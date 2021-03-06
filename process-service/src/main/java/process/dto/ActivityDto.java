package process.dto;

import java.util.ArrayList;
import java.util.List;

public class ActivityDto {
    private Long id;
    private String name;
    private List<Long> inputListDocumentTypes = new ArrayList<>();
    private List<Long> outputListDocumentTypes = new ArrayList<>();
    private List<Long> inputList = new ArrayList<>();
    private List<Long> outputList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getInputListDocumentTypes() {
        return inputListDocumentTypes;
    }

    public void setInputListDocumentTypes(List<Long> inputListDocumentTypes) {
        this.inputListDocumentTypes = inputListDocumentTypes;
    }

    public List<Long> getOutputListDocumentTypes() {
        return outputListDocumentTypes;
    }

    public void setOutputListDocumentTypes(List<Long> outputListDocumentTypes) {
        this.outputListDocumentTypes = outputListDocumentTypes;
    }

    public List<Long> getInputList() {
        return inputList;
    }

    public void setInputList(List<Long> inputList) {
        this.inputList = inputList;
    }

    public List<Long> getOutputList() {
        return outputList;
    }

    public void setOutputList(List<Long> outputList) {
        this.outputList = outputList;
    }

    @Override
    public String toString() {
        return "ActivityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inputListDocumentTypes=" + inputListDocumentTypes +
                ", outputListDocumentTypes=" + outputListDocumentTypes +
                ", inputList=" + inputList +
                ", outputList=" + outputList +
                '}';
    }
}
