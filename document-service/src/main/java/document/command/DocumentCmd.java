package document.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import document.domain.Descriptor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCmd {

    private Long id;
    private Long ownerId;
    private String fileName;
    private List<Descriptor> descriptors;

    public DocumentCmd() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Descriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(List<Descriptor> descriptors) {
        this.descriptors = descriptors;
    }

    @Override
    public String toString() {
        return "DocumentCmd{" + "id=" + id + ", ownerId=" + ownerId + ", fileName='" + fileName + ", descriptors="
                + descriptors + '}';
    }
}
