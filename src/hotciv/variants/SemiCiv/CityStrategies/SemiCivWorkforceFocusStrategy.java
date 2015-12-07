package hotciv.variants.SemiCiv.CityStrategies;

import hotciv.framework.WorkforceFocusStrategy;

/**
 * Created by asger on 03/12/15.
 */
public class SemiCivWorkforceFocusStrategy implements WorkforceFocusStrategy {

    private String workforceFocus;

    @Override
    public void setWorkforceFocus(String newWorkforceFocus) {
        this.workforceFocus = newWorkforceFocus;
    }

    @Override
    public String getWorkforceFocus() {
        return workforceFocus;
    }
}
