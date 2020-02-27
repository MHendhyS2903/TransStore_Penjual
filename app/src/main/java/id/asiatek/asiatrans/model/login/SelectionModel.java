package id.asiatek.asiatrans.model.login;

public class SelectionModel {
    public String label;
    public String value;

    public SelectionModel(String _value, String _label) {
        label = _label;
        value = _value;
    }

    public String toString() {
        return (label);
    }
}
