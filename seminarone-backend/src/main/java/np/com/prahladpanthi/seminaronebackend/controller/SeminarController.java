package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.dto.SeminarDto;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.SeminarMapper;
import np.com.prahladpanthi.seminaronebackend.service.ISeminarService;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConstants.SEMINAR)
public class SeminarController extends BaseController {

    private final ISeminarService seminarService;
    private final SeminarMapper seminarMapper;

    private final IUserService userService;

    @Autowired
    public SeminarController(ISeminarService seminarService,
                             SeminarMapper seminarMapper,
                             IUserService userService) {
        super(userService);
        this.seminarService = seminarService;
        this.seminarMapper = seminarMapper;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll() {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", seminarService.getAllCustomSeminars()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

    @GetMapping(APIConstants.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
        final SeminarEntity seminarEntity = seminarService.findById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", seminarMapper.mapToDto(seminarEntity)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(APIConstants.ADD_SEMINAR)
    public ResponseEntity<ResponseDto> save(@RequestBody SeminarDto seminarDto) {
        return new ResponseEntity<>(new ResponseDto("Successfully saved!", seminarService.addNewSeminar(seminarDto)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(APIConstants.UPDATE)
    public ResponseEntity<ResponseDto> update(@RequestBody SeminarDto seminarDto) {
        if (!(seminarDto.getTitle().isBlank() && seminarDto.getStartDate() == null &&
                seminarDto.getEndDate() == null && seminarDto.getLocationEntityId() == null &&
                seminarDto.getTopicEntityId() == null)) {
            throw new InsufficientDataException("Please enter all the details for adding seminar!");
        }
        SeminarEntity seminarEntity = seminarService.update(seminarMapper.mapToEntity(seminarDto));
        return new ResponseEntity<>(new ResponseDto("Successfully updated!", seminarMapper.mapToDto(seminarEntity)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(APIConstants.DELETE)
    public ResponseEntity<ResponseDto> delete(@RequestBody SeminarDto seminarDto) {
        if (!(seminarDto.getTitle().isBlank() && seminarDto.getStartDate() == null &&
                seminarDto.getEndDate() == null && seminarDto.getLocationEntityId() == null &&
                seminarDto.getTopicEntityId() == null)) {
            throw new InsufficientDataException("Please enter all the details for adding seminar!");
        }
        seminarService.delete(seminarMapper.mapToEntity(seminarDto));
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        if (!seminarService.existsById(id)) {
            throw new NotFoundException("Id: " + id + " not found!");
        }
        seminarService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
