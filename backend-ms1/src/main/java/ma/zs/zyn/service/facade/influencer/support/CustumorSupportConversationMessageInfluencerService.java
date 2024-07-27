package ma.zs.zyn.service.facade.influencer.support;

import java.util.List;
import ma.zs.zyn.bean.core.support.CustumorSupportConversationMessage;
import ma.zs.zyn.dao.criteria.core.support.CustumorSupportConversationMessageCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CustumorSupportConversationMessageInfluencerService {



    List<CustumorSupportConversationMessage> findByCustumorSupportConversationId(Long id);
    int deleteByCustumorSupportConversationId(Long id);
    long countByCustumorSupportConversationId(Long id);




	CustumorSupportConversationMessage create(CustumorSupportConversationMessage t);

    CustumorSupportConversationMessage update(CustumorSupportConversationMessage t);

    List<CustumorSupportConversationMessage> update(List<CustumorSupportConversationMessage> ts,boolean createIfNotExist);

    CustumorSupportConversationMessage findById(Long id);

    CustumorSupportConversationMessage findOrSave(CustumorSupportConversationMessage t);

    CustumorSupportConversationMessage findByReferenceEntity(CustumorSupportConversationMessage t);

    CustumorSupportConversationMessage findWithAssociatedLists(Long id);

    List<CustumorSupportConversationMessage> findAllOptimized();

    List<CustumorSupportConversationMessage> findAll();

    List<CustumorSupportConversationMessage> findByCriteria(CustumorSupportConversationMessageCriteria criteria);

    List<CustumorSupportConversationMessage> findPaginatedByCriteria(CustumorSupportConversationMessageCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CustumorSupportConversationMessageCriteria criteria);

    List<CustumorSupportConversationMessage> delete(List<CustumorSupportConversationMessage> ts);

    boolean deleteById(Long id);

    List<List<CustumorSupportConversationMessage>> getToBeSavedAndToBeDeleted(List<CustumorSupportConversationMessage> oldList, List<CustumorSupportConversationMessage> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
