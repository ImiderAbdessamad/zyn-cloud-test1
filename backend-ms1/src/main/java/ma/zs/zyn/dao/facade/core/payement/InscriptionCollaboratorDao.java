package ma.zs.zyn.dao.facade.core.payement;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.payement.InscriptionCollaborator;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InscriptionCollaboratorDao extends AbstractRepository<InscriptionCollaborator,Long>  {

    List<InscriptionCollaborator> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<InscriptionCollaborator> findByPackagingPlanId(Long id);
    int deleteByPackagingPlanId(Long id);
    long countByPackagingPlanCode(String code);


}
