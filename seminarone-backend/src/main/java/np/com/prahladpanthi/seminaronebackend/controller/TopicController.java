package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.dto.TopicDto;
import np.com.prahladpanthi.seminaronebackend.entity.TopicEntity;
import np.com.prahladpanthi.seminaronebackend.exception.AlreadyExistsException;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.TopicMapper;
import np.com.prahladpanthi.seminaronebackend.service.ITopicService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstants.TOPIC)
public class TopicController extends BaseController {

    private ITopicService topicService;

    private TopicMapper topicMapper;

    @Autowired
    public TopicController(ITopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll() {
        List<TopicEntity> topicEntityList = topicService.findAll();
        if (topicEntityList.isEmpty()) throw new NotFoundException("Topics not found!");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", topicMapper.mapToDto(topicEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstants.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", topicMapper.mapToDto(topicService.findById(id))), HttpStatus.OK);
    }

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

    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        topicService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
