package backend.academy;

import backend.academy.Primitives.Pixel;
import backend.academy.Transformations.DiskTransformation;
import backend.academy.Transformations.HeartTransformation;
import backend.academy.Transformations.PolarTransformation;
import backend.academy.Transformations.SinusoidalTransformation;
import backend.academy.Transformations.SphericalTransformation;
import backend.academy.Transformations.Transformation;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static com.google.common.base.Ascii.equalsIgnoreCase;

@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
public class CLI {
    private static final String COMMAND_GENERATE = "generate";
    private static final String COMMAND_EXIT = "exit";
    private final Map<Integer, Transformation> transformations = new HashMap<>();
    private final Map<Integer, String> transformationNames = new HashMap<>();

    private final Generator generator = new Generator();
    private final GammaCorrector corrector = new GammaCorrector();
    private final Saver saver = new Saver();

    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    public void runInterface() {
        System.out.println("Welcome to Fractal Flame Generator!");

        while (true) {
            initializeTransformations();
            System.out.println("Please enter a command (\"generate\" to generate a fractal, \"exit\" to quit):");
            String input = scanner.nextLine().strip();

            if (equalsIgnoreCase(COMMAND_GENERATE, input)) {
                generateFractal();
            } else if (equalsIgnoreCase(COMMAND_EXIT, input)) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }
    }

    private void generateFractal() {
        System.out.println("Please enter a fractal width");
        int width = getCorrectInt();

        System.out.println("Please enter a fractal height");
        int height = getCorrectInt();

        System.out.println("Please enter dots amount");
        int dotsAmount = getCorrectInt();

        System.out.println("Please enter a iteration count while fractal will be generating");
        int iterationCount = getCorrectInt();

        System.out.println("Please select transformations, which you would like to use:");
        ArrayList<Transformation> fractalTransformations = getTransformations();

        System.out.println("Enter symmetry parameter (just a positive integer):  ");
        int symmetry = getCorrectInt();

        System.out.println("Enter number of threads that will be used: ");
        int threadsAmount = getCorrectInt();

        System.out.println("Please enter file where fractal will be saved (without extension, it will save in png): ");
        Path filePath = Path.of(scanner.nextLine() + ".png");

        ArrayList<ArrayList<Pixel>> picture =
            generator.initializeMultipleThreads(width,
                height,
                iterationCount,
                fractalTransformations, dotsAmount,
                symmetry,
                threadsAmount);
        corrector.correctImage(picture);
        saver.savePicture(picture, filePath);
    }

    private void initializeTransformations() {
        transformations.put(1, new DiskTransformation());
        transformations.put(2, new HeartTransformation());
        transformations.put(3, new PolarTransformation());
        transformations.put(4, new SinusoidalTransformation());
        transformations.put(5, new SphericalTransformation());

        transformationNames.put(1, "Disk Transformation");
        transformationNames.put(2, "Heart Transformation");
        transformationNames.put(3, "Polar Transformation");
        transformationNames.put(4, "Sinusoidal Transformation");
        transformationNames.put(5, "Spherical Transformation");
    }

    private int getCorrectInt() {
        int result;

        while (true) {
            result = scanner.nextInt();
            if (result <= 0) {
                scanner.nextLine();
                System.out.println("Wrong input. Please enter a positive integer :");
            }
            break;
        }

        scanner.nextLine();
        return result;
    }

    private boolean isInteger(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return s.length() <= 11;
    }

    private ArrayList<Transformation> getTransformations() {
        ArrayList<Transformation> fractalTransformations = new ArrayList<>();
        System.out.println("Please enter a number of transformation to pick it ( or \"exit\" to quit choosing menu):");
        while (true) {
            transformationNames.forEach((key, name) -> System.out.println(key + ": " + name));
            String input = scanner.nextLine().strip();

            if (isInteger(input)) {
                int key = Integer.parseInt(input);
                if (transformations.containsKey(key)) {
                    fractalTransformations.add(transformations.get(key));
                    transformationNames.remove(key);
                }
            } else if (equalsIgnoreCase(COMMAND_EXIT, input)) {
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
        return fractalTransformations;
    }

}
