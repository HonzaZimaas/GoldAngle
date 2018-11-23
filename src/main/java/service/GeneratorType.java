package service;

public enum GeneratorType {
    XORSHIFT("Generator XORSHIFT"),
    LCGSHIFT("Generator LCGSHIFT"),
    MT19937("Generator MT19937");

    private String generatorName;

    GeneratorType(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getGeneratorName() {
        return generatorName;
    }
}
