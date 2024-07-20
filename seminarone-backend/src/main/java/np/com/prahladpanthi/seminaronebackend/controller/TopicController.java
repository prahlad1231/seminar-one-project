package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.dto.TopicDto;
import np.com.prahladpanthi.seminaronebackend.entity.TopicEntity;
import np.com.prahladpanthi.seminaronebackend.exception.AlreadyExistsException;
import np.com.prahladpanthi.seminaronebackend.exception.CannotDeleteDataException;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.TopicMapper;
import np.com.prahladpanthi.seminaronebackend.service.ITopicService;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import np.com.prahladpanthi.seminaronebackend.util.BeanCopyUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(APIConstants.TOPIC)
public class TopicController extends BaseController {

    private final ITopicService topicService;

    private final TopicMapper topicMapper;

    private final IUserService userService;

    @Autowired
    public TopicController(ITopicService topicService,
                           TopicMapper topicMapper,
                           IUserService userService) {
        super(userService);
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll() {
        List<TopicEntity> topicEntityList = topicService.findAll();
        if (topicEntityList.isEmpty()) throw new NotFoundException("Topics not found!");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", topicMapper.mapToDto(topicEntityList)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(APIConstants.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", topicMapper.mapToDto(topicService.findById(id))), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(APIConstants.SAVE)
    public ResponseEntity<ResponseDto> save(@RequestBody TopicDto topicDto) {
        if (topicDto.getName().isEmpty()) {
            throw new InsufficientDataException("Please provide all the details!");
        }
        if (topicService.existsByName(topicDto.getName())) throw new AlreadyExistsException("Topic already exists!");
        TopicEntity te = topicMapper.mapToEntity(topicDto);
        TopicEntity topicEntity = topicService.save(te);
        return new ResponseEntity<>(new ResponseDto("Successfully saved!", topicMapper.mapToDto(topicEntity)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(APIConstants.UPDATE)
    public ResponseEntity<ResponseDto> update(@RequestBody TopicDto topicDto) {
        if (topicDto.getId() == null || topicDto.getName() == null) throw new InsufficientDataException("Please provide all details!");
        TopicEntity topicEntity = topicService.findById(topicDto.getId());
        TopicDto updatedTopic = topicMapper.mapToDto(topicEntity);

        BeanCopyUtils.copyNonNullProperties(topicDto, updatedTopic);

        topicService.update(topicMapper.mapToEntity(updatedTopic));

        return new ResponseEntity<>(new ResponseDto("Successfully updated!", updatedTopic), HttpStatus.OK);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        try {
            topicService.deleteById(id);
        } catch (Exception ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new CannotDeleteDataException("Cannot delete topic! It is used by an existing seminar!", ex);
            } else {
                throw new CannotDeleteDataException("Error deleting topic!", ex);
            }
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
