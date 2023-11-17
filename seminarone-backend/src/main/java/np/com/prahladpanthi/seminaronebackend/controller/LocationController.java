package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.LocationDto;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.LocationEntity;
import np.com.prahladpanthi.seminaronebackend.exception.AlreadyExistsException;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.LocationMapper;
import np.com.prahladpanthi.seminaronebackend.service.ILocationService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstants.LOCATION)
public class LocationController extends BaseController {

    private ILocationService locationService;

    private LocationMapper locationMapper;

    @Autowired
    public LocationController(ILocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll() {
        List<LocationEntity> locationEntityList = locationService.findAll();
        if (locationEntityList.isEmpty()) throw new NotFoundException("Location not found!");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", locationMapper.mapToDto(locationEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstants.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
        LocationEntity locationEntity = locationService.findById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", locationMapper.mapToDto(locationEntity)), HttpStatus.OK);
    }

    @PostMapping(APIConstants.SAVE)
    public ResponseEntity<ResponseDto> save(@RequestBody LocationDto locationDto) {
        if (locationDto.getVenueName().isEmpty() || locationDto.getStreetName().isEmpty() || locationDto.getStreetNumber() == null || locationDto.getState() == null) {
            throw new InsufficientDataException("Please provide all the details!");
        }
        if (locationService.existsByVenueName(locationDto.getVenueName())) throw new AlreadyExistsException("Venue name " + locationDto.getVenueName() + " already exists!");
        LocationEntity locationEntity = locationService.save(locationMapper.mapToEntity(locationDto));
        return new ResponseEntity<>(new ResponseDto("Successfully saved!", locationMapper.mapToDto(locationEntity)), HttpStatus.CREATED);
    }

    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        locationService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
