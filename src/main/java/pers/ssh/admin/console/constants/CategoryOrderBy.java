package pers.ssh.admin.console.constants;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 12:01 下午
 * Description:
 */
public enum CategoryOrderBy {

    ASC("asc"),
    DSC("dsc");

    private final String value;

    CategoryOrderBy(final String value) {
        this.value = value;
    }

    private String value() {
        return this.value;
    }

    public static CategoryOrderBy findByValue(final String value) {
        final CategoryOrderBy[] t = CategoryOrderBy.values();
        for (final CategoryOrderBy c : t) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        return null;
    }
}
