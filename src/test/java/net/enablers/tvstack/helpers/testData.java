package net.enablers.tvstack.helpers;
import cucumber.api.Transformer;

public class testData extends Transformer<String> {

    public String transform(String value)
    {
        if(genericFunction.dictTestData.containsKey(value))
        {
            value = genericFunction.dictTestData.get(value);
        }
        return value;
    }
}
