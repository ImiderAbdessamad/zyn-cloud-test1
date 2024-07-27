package ma.zs.zyn.service.impl.collaborator.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.Influencer;
import ma.zs.zyn.dao.criteria.core.coupon.InfluencerCriteria;
import ma.zs.zyn.dao.facade.core.coupon.InfluencerDao;
import ma.zs.zyn.dao.specification.core.coupon.InfluencerSpecification;
import ma.zs.zyn.service.facade.collaborator.coupon.InfluencerCollaboratorService;
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


import java.time.LocalDateTime;
import ma.zs.zyn.zynerator.security.service.facade.UserService;
import ma.zs.zyn.zynerator.security.service.facade.RoleService;
import ma.zs.zyn.zynerator.security.service.facade.RoleUserService;
import ma.zs.zyn.zynerator.security.bean.Role;
import ma.zs.zyn.zynerator.security.bean.RoleUser;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class InfluencerCollaboratorServiceImpl implements InfluencerCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Influencer update(Influencer t) {
        Influencer loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Influencer.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Influencer findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Influencer findOrSave(Influencer t) {
        if (t != null) {
            Influencer result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Influencer> findAll() {
        return dao.findAll();
    }

    public List<Influencer> findByCriteria(InfluencerCriteria criteria) {
        List<Influencer> content = null;
        if (criteria != null) {
            InfluencerSpecification mySpecification = constructSpecification(criteria);
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


    private InfluencerSpecification constructSpecification(InfluencerCriteria criteria) {
        InfluencerSpecification mySpecification =  (InfluencerSpecification) RefelexivityUtil.constructObjectUsingOneParam(InfluencerSpecification.class, criteria);
        return mySpecification;
    }

    public List<Influencer> findPaginatedByCriteria(InfluencerCriteria criteria, int page, int pageSize, String order, String sortField) {
        InfluencerSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InfluencerCriteria criteria) {
        InfluencerSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<Influencer> delete(List<Influencer> list) {
		List<Influencer> result = new ArrayList();
        if (list != null) {
            for (Influencer t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }


    public Influencer findWithAssociatedLists(Long id){
        Influencer result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Influencer> update(List<Influencer> ts, boolean createIfNotExist) {
        List<Influencer> result = new ArrayList<>();
        if (ts != null) {
            for (Influencer t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Influencer loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Influencer t, Influencer loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Influencer findByReferenceEntity(Influencer t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Influencer> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Influencer>> getToBeSavedAndToBeDeleted(List<Influencer> oldList, List<Influencer> newList) {
        List<List<Influencer>> result = new ArrayList<>();
        List<Influencer> resultDelete = new ArrayList<>();
        List<Influencer> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Influencer> oldList, List<Influencer> newList, List<Influencer> resultUpdateOrSave, List<Influencer> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Influencer myOld = oldList.get(i);
                Influencer t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Influencer myNew = newList.get(i);
                Influencer t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    @Override
    public Influencer create(Influencer t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.INFLUENCER);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Influencer mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }

        return mySaved;
     }

    public Influencer findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }




    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    public InfluencerCollaboratorServiceImpl(InfluencerDao dao) {
        this.dao = dao;
    }

    private InfluencerDao dao;
}
