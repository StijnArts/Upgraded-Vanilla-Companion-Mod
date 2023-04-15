package drai.dev.upgradedvanilla;

public class SourceMod {
	private String id;
	private boolean initialized = false;

	public SourceMod(String id){
		this.id = id;
	}

	public void setInitialized() {
		this.initialized = true;
	}
	public boolean isInitialized() {
		return initialized;
	}

	public String getId() {
		return id;
	}
}
