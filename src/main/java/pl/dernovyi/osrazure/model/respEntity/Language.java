package pl.dernovyi.osrazure.model.respEntity;

public enum Language {
    unk("AutoDetect"), da("Danish"), en ("English"),  de("German"), pl ("Polish"), ru ("Russian"),
    pt(" Portuguese"), nl("Dutch"),fr("French"), ar("Language");

    Language(String name){
        this.lanName = name;
    }
    private String lanName;
}
