package cucumber.runtime.arquillian.feature;

public final class Features {
    private Features() {
        // no-op
    }

    public static String featurePath(final Class<?> javaClass) {
        return javaClass.getPackage().getName().replace('.', '/')
                + '/' + createClassNameSubPackage(javaClass.getSimpleName()) + ".feature";
    }

    private static String createClassNameSubPackage(final String name) {
        String result = name;
        if (result.endsWith("Test")) {
            result = result.substring(0, result.length() - "Test".length());
        } else if (result.endsWith("IT")) {
            result = result.substring(0, result.length() - "IT".length());
        }

        if (result.length() == 1) {
            return result;
        }

        return Character.toLowerCase(result.charAt(0)) + replaceUpperCaseWithADashAndLowercase(result.substring(1));
    }

    private static String replaceUpperCaseWithADashAndLowercase(final String substring) {
        final StringBuilder builder = new StringBuilder();
        for (final char c : substring.toCharArray()) {
            if (!Character.isUpperCase(c)) {
                builder.append(c);
            } else {
                builder.append('-').append(Character.toLowerCase(c));
            }
        }

        final String s = builder.toString();
        if (s.endsWith("-")) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

}