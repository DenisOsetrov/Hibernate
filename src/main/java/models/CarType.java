package models;

public enum CarType {
    PASSENGER("Пасажирський"),
    CARGO("Вантажний");

    // Кожне значення має додаткове поле displayName, яке представляє зрозумілу назву типу автомобіля.
    private String displayName;

    // Конструктор
    CarType(String displayName) {
        this.displayName = displayName;
    }

    // Метод повертає значення displayName для відповідного типу автомобіля.
    // Наприклад, CarType.PASSENGER.getDisplayName() поверне рядок "Пасажирський".
    public String getDisplayName() {
        return displayName;
    }
}
