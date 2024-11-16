package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.dto.SeminarDto;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.SeminarMapper;
import np.com.prahladpanthi.seminaronebackend.repository.LocationRepository;
import np.com.prahladpanthi.seminaronebackend.repository.SeminarRepository;
import np.com.prahladpanthi.seminaronebackend.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SeminarServiceImplTest {

    @Mock
    private SeminarRepository seminarRepository;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private SeminarMapper seminarMapper;

    @InjectMocks
    private SeminarServiceImpl seminarService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getAllSeminar() {
        // Arrange
        List<SeminarEntity> seminarEntityList = Arrays.asList(
          new SeminarEntity(), new SeminarEntity()
        );

        List<SeminarDto> seminarDtoList = Arrays.asList(
                new SeminarDto(), new SeminarDto()
        );

        when(seminarRepository.findAll()).thenReturn(seminarEntityList);
        when(seminarMapper.mapToDto(seminarEntityList)).thenReturn(seminarDtoList);

        // Act
        List<SeminarDto> result = seminarService.getAllSeminars();

        // Assert
        assertEquals(2, result.size());
        verify(seminarRepository).findAll(); // verify that findAll() is called from repository
        verify(seminarMapper).mapToDto(seminarEntityList); // verify that mapToDto() is called
    }



}