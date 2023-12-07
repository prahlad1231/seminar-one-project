package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.dto.SeminarDto;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

import java.util.List;

public interface ISeminarService extends IBaseService<SeminarEntity, Long> {

    List<SeminarDto> getAllSeminars();
    SeminarDto addNewSeminar(SeminarDto seminarDto);
}
