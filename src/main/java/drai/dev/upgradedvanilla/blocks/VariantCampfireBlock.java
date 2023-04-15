//package drai.dev.upgradedvanilla.blocks;
//
//import net.fabricmc.fabric.api.object.builder.v1.block.*;
//import net.minecraft.core.*;
//import net.minecraft.core.particles.*;
//import net.minecraft.stats.*;
//import net.minecraft.world.*;
//import net.minecraft.world.damagesource.*;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.player.*;
//import net.minecraft.world.item.*;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.item.enchantment.*;
//import net.minecraft.world.level.*;
//import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.entity.*;
//import net.minecraft.world.level.block.state.*;
//import net.minecraft.world.level.material.*;
//import net.minecraft.world.phys.*;
//import net.minecraft.world.phys.shapes.*;
//
//import javax.swing.text.html.*;
//
//import java.util.*;
//
//public class VariantCampFireBlock extends CampfireBlock implements SimpleWaterloggedBlock {
//	private final boolean smoke;
//	private final int fireDamage;
//
//	public VariantCampFireBlock(int fireDamage) {
//		super(true, fireDamage , FabricBlockSettings.copyOf(Blocks.CAMPFIRE));
//		this.smoke = true;
//		this.fireDamage = fireDamage;
//	}
//
//	public static void extinguish(Level level, BlockPos pos, BlockState state) {
//		if (level.isClientSide && state.getValue(LIT)) {
//			for (int i = 0; i < 20; ++i) {
//				spawnSmokeParticles(level, pos, state.getValue(SIGNAL_FIRE), true);
//			}
//			level.setBlockState(pos, state.with(LIT, false), 3);
//			level.updateNeighbors(pos, state.getBlock());
//		}
//		BlockEntity tileentity = level.getBlockEntity(pos);
//		if (tileentity instanceof CampFireBlockEntity te) {
//			te.dropAllItems();
//		}
//	}
//
//	public static void spawnSmokeParticles(Level worldIn, BlockPos pos, boolean isSignalFire, boolean spawnExtraSmoke) {
//		net.minecraft.util.math.random.Random random = worldIn.getRandom();
//		DefaultParticleType basicparticletype = isSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
//		worldIn.addParticle(basicparticletype, true, (double) pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + random.nextDouble() + random.nextDouble(), (double) pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
//		if (spawnExtraSmoke) {
//			worldIn.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.25D + random.nextDouble() / 2.0D * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + 0.4D, (double) pos.getZ() + 0.25D + random.nextDouble() / 2.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
//		}
//	}
//
//	@Override
//	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
//		ItemStack itemStack;
//		CampfireBlockEntity campfireBlockEntity;
//		Optional<CampfireCookingRecipe> optional;
//		BlockEntity blockEntity = level.getBlockEntity(pos);
//		if (blockEntity instanceof CampfireBlockEntity && (optional = (campfireBlockEntity = (CampfireBlockEntity)blockEntity).getCookableRecipe(itemStack = player.getItemInHand(hand))).isPresent()) {
//			if (!level.isClientSide && campfireBlockEntity.placeFood(player, player.getAbilities().instabuild ? itemStack.copy() : itemStack, optional.get().getCookingTime())) {
//				player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
//				return InteractionResult.SUCCESS;
//			}
//			return InteractionResult.CONSUME;
//		}
//		return InteractionResult.PASS;
//	}
//
//	@Override
//	public void onEntityCollision(BlockState state, Level level, BlockPos pos, Entity entityIn) {
//		if (!entityIn.isFireImmune() && state.get(LIT) && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
//			entityIn.damage(DamageSource.IN_FIRE, (float) this.fireDamage);
//		}
//		super.onEntityCollision(state, level, pos, entityIn);
//	}
//
//	@Override
//	public void onStateReplaced(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean moved) {
//		if (!state.isOf(newState.getBlock())) {
//			BlockEntity tileentity = worldIn.getBlockEntity(pos);
//			if (tileentity instanceof CampFireBlockEntity te) {
//				ItemScatterer.spawn(worldIn, pos, te.getInventory());
//			}
//			super.onStateReplaced(state, worldIn, pos, newState, moved);
//		}
//	}
//
//	@Nullable
//	@Override
//	public BlockState getPlacementState(ItemPlacementContext context) {
//		Level iworld = context.getWorld();
//		BlockPos blockpos = context.getBlockPos();
//		boolean flag = iworld.getFluidState(blockpos).getFluid().matchesType(Fluids.WATER);
//		return this.getDefaultState().with(WATERLOGGED, flag).with(SIGNAL_FIRE, this.isHayBlock(iworld.getBlockState(blockpos.down()))).with(LIT, !flag).with(FACING, context.getPlayerFacing());
//	}
//
//	boolean isHayBlock(BlockState stateIn) {
//		return stateIn.isOf(Blocks.HAY_BLOCK);
//	}
//
//	@Override
//	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess level, BlockPos pos, BlockPos neighborPos) {
//		if (direction == Direction.DOWN) {
//			return state.with(SIGNAL_FIRE, this.isHayBlock(neighborState));
//		}
//		return super.getStateForNeighborUpdate(state, direction, neighborState, level, pos, neighborPos);
//	}
//
//	@Override
//	public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
//		return SHAPE;
//	}
//
//	@Override
//	public BlockRenderType getRenderType(BlockState state) {
//		return BlockRenderType.MODEL;
//	}
//
//	public void randomDisplayTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
//		if (!canBeLit(stateIn, worldIn, pos)) {
//			extinguish(worldIn, pos, stateIn);
//			worldIn.updateListeners(pos, stateIn, stateIn, 3);
//		}
//		if (stateIn.get(LIT)) {
//			if (rand.nextInt(10) == 0) {
//				worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
//			}
//			if (this.smoke && rand.nextInt(5) == 0) {
//				for (int i = 0; i < rand.nextInt(1) + 1; ++i) {
//					worldIn.addParticle(ParticleTypes.LAVA, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, rand.nextFloat() / 2.0F, 5.0E-5D, rand.nextFloat() / 2.0F);
//				}
//			}
//		}
//	}
//
//	public boolean canBeLit(BlockState state, Level level, BlockPos pos) {
//		return !state.get(WATERLOGGED) ;
//	}
//
//	public void toggleLight(Level worldIn, BlockState state, BlockPos pos) {
//		state = state.with(LIT, !state.get(LIT));
//		worldIn.setBlockState(pos, state, 2);
//		if (!state.get(LIT)) {
//			worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.3f, 1.0f);
//		}
//		worldIn.updateNeighbors(pos, this);
//	}
//
//	@Nullable
//	@Override
//	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
//		return (level1, blockPos, blockState, t) -> {
//			if (t instanceof CampFireBlockEntity tile) {
//				if (level1.isClient()) {
//					tile.clientTick();
//				} else {
//					tile.serverTick();
//				}
//			}
//		};
//	}
//
//	@Nullable
//	@Override
//	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//		return new CampFireBlockEntity(pos, state);
//	}
//
//	public RenderLayer getCustomRenderType() {
//		return RenderLayer.getCutoutMipped();
//	}
//}
