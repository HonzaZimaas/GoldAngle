package service;

import io.jenetics.prngine.LCG64ShiftRandom;
import io.jenetics.prngine.MT19937_64Random;
import io.jenetics.prngine.XOR64ShiftRandom;

public class NumberGenerator {
    private GeneratorType generatorType;

    private MT19937_64Random mtRandom;
    private LCG64ShiftRandom lcgShift;
    private XOR64ShiftRandom xorShift;


    public NumberGenerator(GeneratorType type) {
        this.generatorType = type;

        mtRandom = new MT19937_64Random();
        lcgShift = new LCG64ShiftRandom();
        xorShift = new XOR64ShiftRandom();

    }

    public int nextNumber(int range) {
        switch (generatorType) {
            case MT19937:
                return mtRandom.nextInt(0, range);
            case LCGSHIFT:
                return lcgShift.nextInt(0, range);
            case XORSHIFT:
                return xorShift.nextInt(0, range);
            default:
                throw new IllegalStateException(generatorType.toString());
        }
    }


}
