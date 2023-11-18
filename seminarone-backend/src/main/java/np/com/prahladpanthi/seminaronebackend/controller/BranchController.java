package np.com.prahladpanthi.seminaronebackend.controller;

import jakarta.validation.constraints.NotNull;
import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.exception.AlreadyExistsException;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.BranchMapper;
import np.com.prahladpanthi.seminaronebackend.service.IBranchService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstants.BRANCH)
public class BranchController extends BaseController {

    private IBranchService branchService;

    private BranchMapper branchMapper;

    @Autowired
    public BranchController(IBranchService branchService, BranchMapper branchMapper) {
        this.branchService = branchService;
        this.branchMapper = branchMapper;
    }

    @GetMapping(APIConstants.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", branchMapper.mapToDto(branchService.findById(id))), HttpStatus.OK);
    }

    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll() {
        List<BranchEntity> branchEntityList = branchService.findAll();
        if (branchEntityList.isEmpty()) throw new NotFoundException("Branch not found!");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", branchMapper.mapToDto(branchEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstants.FIND_BY_BRANCH_NUMBER)
    public ResponseEntity<ResponseDto> findByBranchNumber(@PathVariable("branchNumber") Integer branchNumber) {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!", branchService.findByBranchNumber(branchNumber)), HttpStatus.OK);
    }

    @PostMapping(APIConstants.SAVE)
    public ResponseEntity<ResponseDto> save(@RequestBody @NotNull BranchDto branchDto) {
        if (branchDto.getAddress().isEmpty() || branchDto.getTelephone().isEmpty()) {
            throw new InsufficientDataException("Please provide all the required data!");
        }
        if (branchService.existsByBranchNumber(branchDto.getBranchNumber())) throw new AlreadyExistsException("Branch already exists! Please check the branch number!");
        BranchEntity branchEntity = branchService.save(branchMapper.mapToEntity(branchDto));
        return new ResponseEntity<>(new ResponseDto("Successfully saved!", branchMapper.mapToDto(branchEntity)), HttpStatus.CREATED);
    }

    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        branchService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
