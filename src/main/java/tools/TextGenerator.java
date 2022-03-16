package tools;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextGenerator {

    // небуквенные символы для генерации текста
    private static String[] nonLetterSymbols = {", ", ". ", "! ", "? ", " - ", ": "};
    // рандом-генератор для генерации числовых значений
    private static Random random = new Random(System.currentTimeMillis());
    // частота встречи пробельных символов (из 10)
    private final static int SPACE_FREQUENCY = 8;
    // длина одной строки генерируемого текста в символах
    private final static int ONE_LINE_LENGTH = 50;

    public static void main(String[] args) {
        // генерируем текст
        String text = generateText(100, 10);

        // создаем ф-цию через функциональный интерфейс для преобразования входного текста во множество слов
        // последовательно используем разбиение текста на отдельные слова, фильтрацию только слов, состоящих из буквенных
        // символов; сортировку сформированного набора значений и их преобразование в коллекцию-множество
        Function<String, Set<String>> function = s -> Arrays.stream(text.split("\\s+"))
                .filter(el -> el.matches("\\w+"))
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        // применяем нашу функцию для генерации множества-словаря
        Set<String> words = function.apply(text);
        // выводим на экран сформированный словарь
        for(String word : words) {
            System.out.println(word);
        }
    }

    /**
     * Генерация текста из рандомно сгенерированных слов
     * @param numberOfWords Число слов генерируемого текста
     * @param maxLengthOfWord Максимальная длина в символах генерируемых слов
     * @return Строку, содержащую сгенерированный текст
     */
    public static String generateText(int numberOfWords, int maxLengthOfWord) {
        int lines = 1;
        StringBuilder text = new StringBuilder("");
        for (int i = 0; i < numberOfWords; i++) {
            text.append(generateWord(maxLengthOfWord));
            if (random.nextInt(10) < SPACE_FREQUENCY) {
                text.append(" ");
            } else {
                text.append(nonLetterSymbols[random.nextInt(nonLetterSymbols.length)]);
            }
            if (text.length() / lines > ONE_LINE_LENGTH) {
                text.append('\n');
                lines++;
            }
        }
        return text.toString();
    }

    /**
     * Генерация рандомного слова из буквенных символов
     * @param maxLengthOfWord Максимальная длина в символах генерируемых слов
     * @return Сгенерированное рандомное слово
     */
    public static String generateWord(int maxLengthOfWord) {
        int wordLength = random.nextInt(maxLengthOfWord + 1);
        StringBuilder word = new StringBuilder("");
        for(int i = 0; i < wordLength; i++) {
            word.append((char)(random.nextInt('z'-'a') + 'a'));
        }
        return word.toString();
    }
}
