package service;

public enum GeneratorType {
    PRVNI("Generator ISSE"),
    PRN_GINE("Generator PRNGine"),
    JAVA_RANDOM("Generator Random");

    private String generatorName;

    GeneratorType(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getGeneratorName() {
        return generatorName;
    }
}
