package service;

public enum GeneratorType {
    PRVNI("Generator ISSE"),
    DRUHY("Generator PRII"),
    TRETI("Generator Math");

    private String generatorName;

    GeneratorType(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getGeneratorName() {
        return generatorName;
    }
}
