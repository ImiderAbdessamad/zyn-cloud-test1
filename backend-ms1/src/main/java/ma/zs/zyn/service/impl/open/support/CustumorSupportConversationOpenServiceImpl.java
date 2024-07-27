package ma.zs.zyn.service.impl.open.support;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.support.CustumorSupportConversation;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationCriteria;
import ma.zs.zyn.dao.facade.core.support.CustumorSupportConversationDao;
import ma.zs.zyn.dao.specification.core.support.CustumorSupportConversationSpecification;
import ma.zs.zyn.service.facade.open.support.CustumorSupportConversationOpenService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.open.collaborator.CollaboratorOpenService ;
import ma.zs.zyn.bean.core.collaborator.Collaborator ;
import ma.zs.zyn.service.facade.open.support.AgentOpenService ;
import ma.zs.zyn.bean.core.support.Agent ;
import ma.zs.zyn.service.facade.open.support.CustumorSupportConversationCategoryOpenService ;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationCategory ;
import ma.zs.zyn.service.facade.open.support.CustumorSupportConversationStateOpenService ;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationState ;
import ma.zs.zyn.service.facade.open.support.CustumorSupportConversationMessageOpenService ;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage ;

import java.util.List;
@Service
public class CustumorSupportConversationOpenServiceImpl implements CustumorSupportConversationOpenService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversation update(CustumorSupportConversation t) {
        CustumorSupportConversation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CustumorSupportConversation.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public CustumorSupportConversation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CustumorSupportConversation findOrSave(CustumorSupportConversation t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CustumorSupportConversation result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CustumorSupportConversation> findAll() {
        return dao.findAll();
    }

    public List<CustumorSupportConversation> findByCriteria(CustumorSupportConversationCriteria criteria) {
        List<CustumorSupportConversation> content = null;
        if (criteria != null) {
            CustumorSupportConversationSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CustumorSupportConversationSpecification constructSpecification(CustumorSupportConversationCriteria criteria) {
        CustumorSupportConversationSpecification mySpecification =  (CustumorSupportConversationSpecification) RefelexivityUtil.constructObjectUsingOneParam(CustumorSupportConversationSpecification.class, criteria);
        return mySpecification;
    }

    public List<CustumorSupportConversation> findPaginatedByCriteria(CustumorSupportConversationCriteria criteria, int page, int pageSize, String order, String sortField) {
        CustumorSupportConversationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CustumorSupportConversationCriteria criteria) {
        CustumorSupportConversationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CustumorSupportConversation> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<CustumorSupportConversation> findByAgentId(Long id){
        return dao.findByAgentId(id);
    }
    public int deleteByAgentId(Long id){
        return dao.deleteByAgentId(id);
    }
    public long countByAgentId(Long id){
        return dao.countByAgentId(id);
    }
    public List<CustumorSupportConversation> findByCustumorSupportConversationCategoryId(Long id){
        return dao.findByCustumorSupportConversationCategoryId(id);
    }
    public int deleteByCustumorSupportConversationCategoryId(Long id){
        return dao.deleteByCustumorSupportConversationCategoryId(id);
    }
    public long countByCustumorSupportConversationCategoryCode(String code){
        return dao.countByCustumorSupportConversationCategoryCode(code);
    }
    public List<CustumorSupportConversation> findByCustumorSupportConversationStateCode(String code){
        return dao.findByCustumorSupportConversationStateCode(code);
    }
    public int deleteByCustumorSupportConversationStateCode(String code){
        return dao.deleteByCustumorSupportConversationStateCode(code);
    }
    public long countByCustumorSupportConversationStateCode(String code){
        return dao.countByCustumorSupportConversationStateCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        custumorSupportConversationMessageService.deleteByCustumorSupportConversationId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CustumorSupportConversation> delete(List<CustumorSupportConversation> list) {
		List<CustumorSupportConversation> result = new ArrayList();
        if (list != null) {
            for (CustumorSupportConversation t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CustumorSupportConversation create(CustumorSupportConversation t) {
        CustumorSupportConversation loaded = findByReferenceEntity(t);
        CustumorSupportConversation saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getCustumorSupportConversationMessages() != null) {
                t.getCustumorSupportConversationMessages().forEach(element-> {
                    element.setCustumorSupportConversation(saved);
                    custumorSupportConversationMessageService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public CustumorSupportConversation findWithAssociatedLists(Long id){
        CustumorSupportConversation result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setCustumorSupportConversationMessages(custumorSupportConversationMessageService.findByCustumorSupportConversationId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CustumorSupportConversation> update(List<CustumorSupportConversation> ts, boolean createIfNotExist) {
        List<CustumorSupportConversation> result = new ArrayList<>();
        if (ts != null) {
            for (CustumorSupportConversation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CustumorSupportConversation loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CustumorSupportConversation t, CustumorSupportConversation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(CustumorSupportConversation custumorSupportConversation){
    if(custumorSupportConversation !=null && custumorSupportConversation.getId() != null){
        List<List<CustumorSupportConversationMessage>> resultCustumorSupportConversationMessages= custumorSupportConversationMessageService.getToBeSavedAndToBeDeleted(custumorSupportConversationMessageService.findByCustumorSupportConversationId(custumorSupportConversation.getId()),custumorSupportConversation.getCustumorSupportConversationMessages());
            custumorSupportConversationMessageService.delete(resultCustumorSupportConversationMessages.get(1));
        emptyIfNull(resultCustumorSupportConversationMessages.get(0)).forEach(e -> e.setCustumorSupportConversation(custumorSupportConversation));
        custumorSupportConversationMessageService.update(resultCustumorSupportConversationMessages.get(0),true);
        }
    }








    public CustumorSupportConversation findByReferenceEntity(CustumorSupportConversation t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(CustumorSupportConversation t){
        if( t != null) {
        }
    }



    public List<CustumorSupportConversation> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<CustumorSupportConversation>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversation> oldList, List<CustumorSupportConversation> newList) {
        List<List<CustumorSupportConversation>> result = new ArrayList<>();
        List<CustumorSupportConversation> resultDelete = new ArrayList<>();
        List<CustumorSupportConversation> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<CustumorSupportConversation> oldList, List<CustumorSupportConversation> newList, List<CustumorSupportConversation> resultUpdateOrSave, List<CustumorSupportConversation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CustumorSupportConversation myOld = oldList.get(i);
                CustumorSupportConversation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CustumorSupportConversation myNew = newList.get(i);
                CustumorSupportConversation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private CollaboratorOpenService collaboratorService ;
    @Autowired
    private AgentOpenService agentService ;
    @Autowired
    private CustumorSupportConversationCategoryOpenService custumorSupportConversationCategoryService ;
    @Autowired
    private CustumorSupportConversationStateOpenService custumorSupportConversationStateService ;
    @Autowired
    private CustumorSupportConversationMessageOpenService custumorSupportConversationMessageService ;

    public CustumorSupportConversationOpenServiceImpl(CustumorSupportConversationDao dao) {
        this.dao = dao;
    }

    private CustumorSupportConversationDao dao;
}
