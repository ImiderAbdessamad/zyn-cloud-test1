package ma.zs.zyn.service.impl.open.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.dao.facade.core.coupon.CouponDao;
import ma.zs.zyn.dao.specification.core.coupon.CouponSpecification;
import ma.zs.zyn.service.facade.open.coupon.CouponOpenService;
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

import ma.zs.zyn.service.facade.open.coupon.InfluencerOpenService ;
import ma.zs.zyn.bean.core.coupon.Influencer ;
import ma.zs.zyn.service.facade.open.coupon.CouponStateOpenService ;
import ma.zs.zyn.bean.core.coupon.CouponState ;

import java.util.List;
@Service
public class CouponOpenServiceImpl implements CouponOpenService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Coupon update(Coupon t) {
        Coupon loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Coupon.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Coupon findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Coupon findOrSave(Coupon t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Coupon result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Coupon> findAll() {
        return dao.findAll();
    }

    public List<Coupon> findByCriteria(CouponCriteria criteria) {
        List<Coupon> content = null;
        if (criteria != null) {
            CouponSpecification mySpecification = constructSpecification(criteria);
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


    private CouponSpecification constructSpecification(CouponCriteria criteria) {
        CouponSpecification mySpecification =  (CouponSpecification) RefelexivityUtil.constructObjectUsingOneParam(CouponSpecification.class, criteria);
        return mySpecification;
    }

    public List<Coupon> findPaginatedByCriteria(CouponCriteria criteria, int page, int pageSize, String order, String sortField) {
        CouponSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CouponCriteria criteria) {
        CouponSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Coupon> findByInfluencerId(Long id){
        return dao.findByInfluencerId(id);
    }
    public int deleteByInfluencerId(Long id){
        return dao.deleteByInfluencerId(id);
    }
    public long countByInfluencerId(Long id){
        return dao.countByInfluencerId(id);
    }
    public List<Coupon> findByCouponStateLibelle(String libelle){
        return dao.findByCouponStateLibelle(libelle);
    }
    public int deleteByCouponStateLibelle(String libelle){
        return dao.deleteByCouponStateLibelle(libelle);
    }
    public long countByCouponStateLibelle(String libelle){
        return dao.countByCouponStateLibelle(libelle);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Coupon> delete(List<Coupon> list) {
		List<Coupon> result = new ArrayList();
        if (list != null) {
            for (Coupon t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Coupon create(Coupon t) {
        Coupon loaded = findByReferenceEntity(t);
        Coupon saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Coupon findWithAssociatedLists(Long id){
        Coupon result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Coupon> update(List<Coupon> ts, boolean createIfNotExist) {
        List<Coupon> result = new ArrayList<>();
        if (ts != null) {
            for (Coupon t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Coupon loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Coupon t, Coupon loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Coupon findByReferenceEntity(Coupon t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Coupon t){
        if( t != null) {
        }
    }



    public List<Coupon> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Coupon>> getToBeSavedAndToBeDeleted(List<Coupon> oldList, List<Coupon> newList) {
        List<List<Coupon>> result = new ArrayList<>();
        List<Coupon> resultDelete = new ArrayList<>();
        List<Coupon> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Coupon> oldList, List<Coupon> newList, List<Coupon> resultUpdateOrSave, List<Coupon> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Coupon myOld = oldList.get(i);
                Coupon t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Coupon myNew = newList.get(i);
                Coupon t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private InfluencerOpenService influencerService ;
    @Autowired
    private CouponStateOpenService couponStateService ;

    public CouponOpenServiceImpl(CouponDao dao) {
        this.dao = dao;
    }

    private CouponDao dao;
}
