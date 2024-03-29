package com.tsfsclient;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Branch {
    SHARON_IRONI("שרון עירוני", new Line[]{Line.L_001_HOD_HAHSRON,Line.L_001_KFAR_SABA,Line.L_001_RAANANA,Line.L_001_RAMAT_HASHARON,
            Line.L_002_HERTZELIYA,Line.L_002_KFAR_SABA,Line.L_002_RAANANA,Line.L_003_HERTZELIYA,Line.L_003_KFAR_SABA,Line.L_004_RAANANA,Line.L_004_HOD_HASHARON,
            Line.L_005_HOD_HASHARON,Line.L_005_RAANANA,Line.L_006_RAANANA,Line.L_007_RAANANA,Line.L_008_RAANANA,Line.L_008_KFAR_SABA,Line.L_009_HOD_HASHARON,Line.L_009_HERTZELIYA,
            Line.L_010_KFAR_SABA,Line.L_011_HOD_HASHARON,Line.L_012_HOD_HASHARON,Line.L_013_HERTZELIYA,Line.L_014_KFAR_SABA,Line.L_015_KFAR_SABA,Line.L_016_RAANANA,Line.L_019_HERTZELIYA,Line.L_019_KFAR_SABA,
            Line.L_021_KFAR_SABA,Line.L_024_RAANANA,Line.L_027_HERTZELIYA,Line.L_029_KFAR_SABA,Line.L_030_HOD_HASHARON,Line.L_031_HERTZELIYA,Line.L_032_HERTZELIYA,Line.L_039_KFAR_SABA,Line.L_042_KFAR_SABA,Line.L_048_TEL_AVIV,Line.L_051_RAANANA,
            Line.L_054_RAANANA,Line.L_057_RAANANA,Line.L_061_KFAR_SABA,Line.L_063_HERTZELIYA,Line.L_065_HOD_HASHARON,Line.L_070_KFAR_SABA,Line.L_071_RAANANA,Line.L_072_KFAR_SABA,Line.L_073_HOD_HASHARON,Line.L_074_HOD_HASHARON,
            Line.L_075_HOD_HASHARON,Line.L_075_RAANANA,Line.L_076_HOD_HASHARON,Line.L_076_RAANANA,Line.L_077_HOD_HASHARON,Line.L_077_RAANANA,Line.L_078_HOD_HASHARON,Line.L_079_RAANANA,Line.L_080_KFAR_SABA,Line.L_081_KFAR_SABA,
            Line.L_081_HERTZELIYA,Line.L_082_HERTZELIYA,Line.L_082_RAANANA,Line.L_083_HERTZELIYA,Line.L_083_KFAR_SABA,Line.L_084_HERTZELIYA,Line.L_084_KFAR_SABA,Line.L_084_RAANANA,Line.L_085_HERTZELIYA,Line.L_085_KFAR_SABA,Line.L_086_KFAR_SABA,
            Line.L_087_HERTZELIYA,Line.L_087_KFAR_SABA,Line.L_088_HERTZELIYA,Line.L_088_KFAR_SABA,Line.L_089_HERTZELIYA,Line.L_089_KFAR_SABA,Line.L_090_RAANANA,Line.L_091_KFAR_SABA,Line.L_092_HERTZELIYA,Line.L_094_HERTZELIYA,Line.L_095_HOD_HASHARON,
            Line.L_096_HERTZELIYA,Line.L_096_HOD_HASHARON,Line.L_096_RAANANA,Line.L_097_RAANANA,Line.L_097_KFAR_SABA,Line.L_098_RAANANA,Line.L_099_HERTZELIYA,Line.L_099_KFAR_SABA,Line.L_099_RAANANA,Line.L_228_KFAR_SABA,Line.L_229_KFAR_SABA,
            Line.L_230_KFAR_SABA,Line.L_231_KFAR_SABA,Line.L_249_TEL_AVIV,Line.ELSE}),

    SHARON_BEIN_IRONI("שרון בינעירוני", new Line[]{Line.L_013_KFAR_SABA,Line.L_017_KFAR_SABA,Line.L_018_RAANANA,Line.L_020_KFAR_SABA,Line.L_023_BEIT_BEREL,Line.L_030_HOD_HASHARON,Line.L_037_KFAR_SABA,Line.L_047_TEL_AVIV,
            Line.L_067_ROSH_HAAYIN,Line.L_090_TEL_AVIV,Line.L_091_TEL_AVIV,Line.L_113_KFAR_SABA,Line.L_118_KFAR_SABA,Line.L_123_TEL_AVIV,Line.L_124_TEL_AVIV,Line.L_139_KFAR_SABA,Line.L_140_KFAR_SABA,Line.L_142_KFAR_SABA,Line.L_145_KFAR_SABA,Line.L_147_TEL_AVIV,Line.L_148_TEL_AVIV,
            Line.L_149_TEL_AVIV,Line.L_178_TEL_AVIV,Line.L_213_HERTZELIYA,Line.L_222_KFAR_SABA,Line.L_247_TEL_AVIV,Line.L_248_TEL_AVIV,Line.L_259_KFAR_SABA,Line.L_298_BEIT_BEREL,Line.L_347_TEL_AVIV,Line.L_349_TEL_AVIV,Line.L_468_KALANSUA,Line.L_564_KFAR_SABA,Line.L_570_TEL_AVIV,
            Line.L_061_KFAR_SABA,Line.L_063_HERTZELIYA,Line.L_065_HOD_HASHARON,Line.L_070_KFAR_SABA,Line.L_071_RAANANA,Line.L_072_KFAR_SABA,Line.L_073_HOD_HASHARON,Line.L_074_HOD_HASHARON,
            Line.L_075_HOD_HASHARON,Line.L_075_RAANANA,Line.L_076_HOD_HASHARON,Line.L_076_RAANANA,Line.L_077_HOD_HASHARON,Line.L_077_RAANANA,Line.L_078_HOD_HASHARON,Line.L_079_RAANANA,Line.L_080_KFAR_SABA,Line.L_081_KFAR_SABA,
            Line.L_081_HERTZELIYA,Line.L_082_HERTZELIYA,Line.L_082_RAANANA,Line.L_083_HERTZELIYA,Line.L_083_KFAR_SABA,Line.L_084_HERTZELIYA,Line.L_084_KFAR_SABA,Line.L_084_RAANANA,Line.L_085_HERTZELIYA,Line.L_085_KFAR_SABA,Line.L_086_KFAR_SABA,
            Line.L_087_HERTZELIYA,Line.L_087_KFAR_SABA,Line.L_088_HERTZELIYA,Line.L_088_KFAR_SABA,Line.L_089_HERTZELIYA,Line.L_089_KFAR_SABA,Line.L_090_RAANANA,Line.L_091_KFAR_SABA,Line.L_092_HERTZELIYA,Line.L_094_HERTZELIYA,Line.L_095_HOD_HASHARON,
            Line.L_096_HERTZELIYA,Line.L_096_HOD_HASHARON,Line.L_096_RAANANA,Line.L_097_RAANANA,Line.L_097_KFAR_SABA,Line.L_098_RAANANA,Line.L_099_HERTZELIYA,Line.L_099_KFAR_SABA,Line.L_099_RAANANA,Line.ELSE}),

    DAROM("דרום", new Line[]{Line.L_001_ARAD,Line.L_002_ARAD,Line.L_003_ARAD ,Line.L_005_DAROM,Line.L_010_DAROM,Line.L_011_ARAD,Line.L_012_ARAD,Line.L_015_DAROM,Line.L_020_DAROM,Line.L_021_DAROM,Line.L_024_DAROM,Line.L_025_DAROM,Line.L_026_DAROM,
            Line.L_028_DAROM,Line.L_030_DAROM,Line.L_036_DAROM,Line.L_037_DAROM,Line.L_044_DAROM,Line.L_045_DAROM,Line.L_046_DAROM,Line.L_047_DAROM,Line.L_050_DAROM,Line.L_054_DAROM,Line.L_055_DAROM,Line.L_056_DAROM,Line.L_059_DAROM,
            Line.L_060_DAROM,Line.L_062_DAROM,Line.L_064_DAROM,Line.L_065_DAROM,Line.L_066_DAROM,Line.L_067_DAROM,Line.L_068_DAROM,Line.L_070_DAROM,Line.L_074_DAROM,Line.L_080_ARAD,Line.L_117_DAROM,Line.L_126_DAROM,Line.L_140_DAROM,Line.L_142_DAROM,
            Line.L_144_DAROM,Line.L_145_DAROM,Line.L_150_DAROM,Line.L_151_DAROM,Line.L_152_DAROM,Line.L_153_DAROM,Line.L_158_DAROM,Line.L_160_DAROM,Line.L_161_DAROM,Line.L_163_DAROM,Line.L_170_DAROM,Line.L_322_DAROM,Line.L_340_DAROM,
            Line.L_342_DAROM,Line.L_343_DAROM,Line.L_345_DAROM,Line.L_347_DAROM,Line.L_348_DAROM,Line.L_352_DAROM,Line.L_353_DAROM,Line.L_357_DAROM,Line.L_358_DAROM,Line.L_359_DAROM,Line.L_367_DAROM,Line.L_368_DAROM,Line.L_369_DAROM,Line.L_370_DAROM,Line.L_371_DAROM,
            Line.L_373_DAROM,Line.L_375_DAROM,Line.L_377_DAROM,Line.L_378_DAROM,Line.L_380_DAROM,Line.L_386_ARAD,Line.L_387_ARAD,Line.L_388_ARAD,Line.L_400_ARAD,Line.L_457_DAROM,Line.L_458_DAROM,Line.L_459_DAROM,Line.L_469_DAROM,Line.L_559_DAROM,Line.L_570_DAROM,Line.L_587_ARAD,Line.L_588_ARAD,
            Line.L_589_ARAD,Line.L_660_TEL_AVIV,Line.L_669_DAROM,Line.L_684_DAROM,Line.L_687_DAROM,Line.L_688_DAROM,Line.L_689_DAROM,Line.ELSE}),

    SHAHAM_IRONI("שחמ עירוני", new Line[]{Line.L_001_TIBE,Line.L_003_TIRA,Line.L_004_TIRA,Line.L_006_TEL_AVIV,Line.L_008_RAMAT_HASHARON,Line.L_012_TEL_AVIV,Line.L_013_HOLON,
            Line.L_021_TEL_AVIV, Line.L_022_RISHON_LETZION,Line.L_024_TEL_AVIV,Line.L_026_TEL_AVIV,Line.L_035_HOLON,Line.L_077_HOLON,Line.L_126_TEL_AVIV,Line.L_501_TEL_AVIV,Line.L_524_TEL_AVIV,Line.L_525_TEL_AVIV,Line.L_531_TEL_AVIV,Line.L_532_TEL_AVIV,Line.L_561_KFAR_SABA,
            Line.L_565_TEL_AVIV,Line.L_572_TEL_AVIV,Line.L_909_RAMAT_HASHARON,Line.ELSE}),

    SHAHAM_BEIN_IRONI("שחמ בינעירוני", new Line[]{Line.L_504_TEL_AVIV,Line.L_505_RAANANA,Line.L_510_TEL_AVIV,Line.L_551_HERTZELIYA,Line.L_553_HERTZELIYA,Line.L_567_KFAR_SABA,Line.L_575_TEL_AVIV,Line.L_576_KFAR_SABA,Line.L_600_NETANYA,Line.L_601_NETANYA,Line.L_602_NETANYA,
            Line.L_603_NETANYA,Line.L_604_NETANYA,Line.L_605_NETANYA,Line.L_606_NETANYA,Line.L_607_NETANYA,Line.L_608_NETANYA,Line.L_609_NETANYA,Line.L_610_NETANYA,Line.L_611_NETANYA,Line.L_612_NETANYA,Line.L_613_NETANYA,Line.L_614_NETANYA,Line.L_615_NETANYA,Line.L_616_NETANYA,Line.L_617_NETANYA,Line.L_619_NETANYA,
            Line.L_621_NETANYA,Line.L_623_NETANYA,Line.L_624_NETANYA,Line.L_650_NETANYA,Line.L_699_NETANYA,Line.ELSE}),

    ELAD("אלעד", new Line[]{Line.L_004_ELAD,Line.L_005_ELAD,Line.L_006_ELAD,Line.L_173_ELAD,Line.L_174_ELAD,Line.L_176_ELAD,Line.L_179_ELAD,Line.L_180_ELAD,Line.L_251_ELAD,Line.L_270_ELAD,Line.L_276_ELAD,Line.L_277_ELAD,Line.L_278_ELAD,Line.L_279_ELAD,
            Line.L_280_ELAD,Line.L_281_ELAD,Line.L_282_ELAD,Line.L_283_ELAD,Line.L_377_ELAD,Line.L_379_ELAD,Line.L_380_ELAD,Line.L_444_ELAD,Line.L_445_ELAD,Line.L_446_ELAD,Line.L_453_ELAD,Line.L_477_ELAD,Line.L_577_ELAD,Line.L_590_ELAD, Line.L_595_ELAD,Line.L_596_ELAD, Line.L_597_ELAD, Line.ELSE}),

    ONO("אונו", new Line[]{Line.L_001_OR_YAHODA,Line.L_002_ONO,Line.L_007_YAHOD,Line.L_007_BNEI_BRAK, Line.L_008_ONO,Line.L_010_ONO_ADD,Line.L_011_OR_YAHODA,Line.L_015_ONO,Line.L_018_ONO,Line.L_028_ONO,Line.L_036_ONO,Line.L_037_ONO,Line.L_055_ONO,Line.L_056_ONO,Line.L_058_ONO,Line.L_059_ONO,Line.L_068_ONO,Line.L_069_ONO,Line.L_076_ONO,
            Line.L_077_ONO,Line.L_078_ONO,Line.L_079_ONO,Line.L_085_ONO,Line.L_115_ONO,Line.L_137_ONO,Line.L_138_ONO,Line.L_139_ONO,Line.L_168_ONO,Line.L_205_ONO,Line.L_236_ONO,Line.L_256_ONO,Line.L_385_ONO,Line.L_398_ONO,Line.L_436_ONO, Line.ELSE});



    private final String displayName;
    private final Line[] lines;

    @Override
    public String toString() {
        return displayName;
    }

    public static List<Branch> sort() {
        List<Branch> branches = Arrays.asList(Branch.values());
        branches.sort(Comparator.comparing(Branch::toString));
        return branches;
    }

    public Line[] getLines() {
        return lines;
    }

    public boolean isLineAsStringInBranch(String lineInString){
        boolean res = false;
        for (Line line : lines){
            if(line.toString().equals(lineInString)){
                res = true;
                break;
            }
        }
        return res;
    }

    Branch(String displayName, Line[] lines) {
        this.displayName = displayName;
        this.lines = lines;
    }
}
