public class MeasurementApplication {

    public static void main(String[] args) {

        Measurement<LengthType> feet =
                new Measurement<>(1,
                        LengthType.FEET);

        Measurement<LengthType> inch =
                new Measurement<>(12,
                        LengthType.INCH);

        System.out.println(
                "1 Feet == 12 Inch : "
                        + feet.equals(inch)
        );

        Measurement<WeightType> kg =
                new Measurement<>(1,
                        WeightType.KILOGRAM);

        Measurement<WeightType> gram =
                new Measurement<>(1000,
                        WeightType.GRAM);

        System.out.println(
                "1 Kg == 1000 Gram : "
                        + kg.equals(gram)
        );

        Measurement<VolumeType> litre =
                new Measurement<>(1,
                        VolumeType.LITRE);

        Measurement<VolumeType> ml =
                new Measurement<>(1000,
                        VolumeType.MILLILITRE);

        System.out.println(
                "1 Litre == 1000 mL : "
                        + litre.equals(ml)
        );

        Measurement<VolumeType> gallon =
                new Measurement<>(1,
                        VolumeType.GALLON);

        System.out.println(
                "1 Gallon in Litre : "
                        + gallon.convertTo(
                        VolumeType.LITRE
                )
        );

        Measurement<VolumeType> total =
                litre.add(
                        new Measurement<>(
                                500,
                                VolumeType.MILLILITRE
                        )
                );

        System.out.println(
                "1 Litre + 500 mL : "
                        + total
        );
    }
}