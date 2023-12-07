package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.dto.SeminarDto;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.SeminarMapper;
import np.com.prahladpanthi.seminaronebackend.repository.LocationRepository;
import np.com.prahladpanthi.seminaronebackend.repository.SeminarRepository;
import np.com.prahladpanthi.seminaronebackend.repository.TopicRepository;
import np.com.prahladpanthi.seminaronebackend.service.ISeminarService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeminarServiceImpl extends BaseServiceImpl<SeminarEntity, Long> implements ISeminarService {

    private SeminarRepository seminarRepository;

    private TopicRepository topicRepository;

    private LocationRepository locationRepository;

    private SeminarMapper seminarMapper;

    @Autowired
    public SeminarServiceImpl(SeminarRepository seminarRepository, TopicRepository topicRepository, LocationRepository locationRepository, SeminarMapper seminarMapper) {
        super(seminarRepository);
        this.seminarRepository = seminarRepository;
        this.topicRepository = topicRepository;
        this.locationRepository = locationRepository;
        this.seminarMapper = seminarMapper;
    }


    @Override
    public List<SeminarDto> getAllSeminars() {
        List<SeminarEntity> seminarEntityList = findAll();

        return seminarMapper.mapToDto(seminarEntityList);

    }

    @Override
    public SeminarDto addNewSeminar(SeminarDto seminarDto) {
        if (seminarDto.getTopicEntityId() == null || seminarDto.getLocationEntityId() == null || seminarDto.getTitle() == null ||
            seminarDto.getStartDate() == null || seminarDto.getEndDate() == null || seminarDto.getPrice() == null) {
            throw new InsufficientDataException("Please enter all the details!");
        }
        if (!(locationRepository.existsById(seminarDto.getLocationEntityId()) || topicRepository.existsById(seminarDto.getTopicEntityId()))) {
            throw new NotFoundException("Location or Topic not found!");
        }

        SeminarEntity seminarEntity = seminarRepository.save(seminarMapper.mapToEntity(seminarDto));
        return seminarMapper.mapToDto(seminarEntity);
    }
}
