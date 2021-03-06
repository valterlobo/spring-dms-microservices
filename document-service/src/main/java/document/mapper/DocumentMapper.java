package document.mapper;

import document.command.DocumentCmd;
import document.domain.Document;
import document.dto.DocumentDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(DocumentMapperDecorator.class)
public interface DocumentMapper {

    DocumentDto mapToModel(Document document);

    Document mapToEntity(DocumentCmd documentCmd) throws IOException;

    List<DocumentDto> mapToModelList(List<Document> documentList);

}
