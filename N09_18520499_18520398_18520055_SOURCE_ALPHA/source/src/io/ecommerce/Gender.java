package io.ecommerce;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String _gender;

    Gender(final String gender) {
        _gender = gender;
    }

    /*
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return _gender;
    }
}