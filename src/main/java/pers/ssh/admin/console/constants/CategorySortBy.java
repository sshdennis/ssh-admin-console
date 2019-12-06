package pers.ssh.admin.console.constants;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 12:00 下午
 * Description:
 */
public enum CategorySortBy {

    PRICE("sort_price"),
    TIME("sort_time");

    private final String value;

    CategorySortBy(final String value) {
        this.value = value;
    }

    private String value() {
        return this.value;
    }

    public static CategorySortBy findByValue(final String value) {
        final CategorySortBy[] t = CategorySortBy.values();
        for (final CategorySortBy c : t) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        return null;
    }
}
