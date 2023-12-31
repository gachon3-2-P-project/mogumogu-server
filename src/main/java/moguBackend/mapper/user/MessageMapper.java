package moguBackend.mapper.user;

import moguBackend.domain.user.ArticleEntity;
import moguBackend.domain.user.MessageEntity;
import moguBackend.domain.user.UserEntity;
import moguBackend.dto.user.MessageDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MessageMapper {

    /**
     * Entity -> Dto
     */
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "user.nickName", target = "nickName")
    MessageDto.MessageResponseDto toResponseDto(MessageEntity messageEntity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "article", source = "articleEntity")
    @Mapping(target = "content", source = "messageRequestDto.content")
    MessageEntity toRequestEntity(MessageDto.MessageRequestDto messageRequestDto, UserEntity userEntity, ArticleEntity articleEntity);


}
