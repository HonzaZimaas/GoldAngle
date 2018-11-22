package service;

import io.jenetics.prngine.KISS64Random;
import io.jenetics.prngine.LCG64ShiftRandom;
import io.jenetics.prngine.PRNG;
import io.jenetics.prngine.internal.SeedRandom;

import java.util.Random;

public class NumberGenerator {
    private GeneratorType generatorType;

    private PRNG prg;
    private PRNG prng;
    private PRNG prgn1;




    public NumberGenerator(GeneratorType type) {
        this.generatorType = type;

        prg= new KISS64Random();
        prng = new SeedRandom();
        prgn1 = new LCG64ShiftRandom();

    }

    public int nextNumber(int range) {
        switch (generatorType) {
            case JAVA_RANDOM:
                return prng.nextInt(range);
            case PRN_GINE:
                return prg.nextInt(0, range);
            case PRVNI:
                return prgn1.nextInt(0, range);
            default:
                throw new IllegalStateException(generatorType.toString());
        }
    }


}
