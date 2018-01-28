package pl.olszak.michal.todo.data

/**
 * Created by molszak.
 * 28.01.2018
 */
enum class Icon(val iconName: String) {

    NONE(""),
    WORK("ic_work"),
    SCHOOL("ic_school"),
    PET("ic_pet"),
    HOUSE("ic_house"),
    MEAL("ic_meal"),
    RELAX("ic_relax");


    companion object {
        fun getIcon(iconName: String): Icon {
            val icon = Icon.values().find { it.iconName == iconName }
            return icon ?: NONE
        }
    }

}