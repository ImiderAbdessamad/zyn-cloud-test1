package  ma.zs.zyn.dao.specification.core.payement;

import ma.zs.zyn.dao.criteria.core.payement.InscriptionCollaboratorCriteria;
import ma.zs.zyn.bean.core.payement.InscriptionCollaborator;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InscriptionCollaboratorSpecification extends  AbstractSpecification<InscriptionCollaboratorCriteria, InscriptionCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicateBigDecimal("consumedEntity", criteria.getConsumedEntity(), criteria.getConsumedEntityMin(), criteria.getConsumedEntityMax());
        addPredicateBigDecimal("consumedProjet", criteria.getConsumedProjet(), criteria.getConsumedProjetMin(), criteria.getConsumedProjetMax());
        addPredicateBigDecimal("consumedAttribut", criteria.getConsumedAttribut(), criteria.getConsumedAttributMin(), criteria.getConsumedAttributMax());
        addPredicateBigDecimal("consumedTokenInput", criteria.getConsumedTokenInput(), criteria.getConsumedTokenInputMin(), criteria.getConsumedTokenInputMax());
        addPredicateBigDecimal("consumedTokenOutput", criteria.getConsumedTokenOutput(), criteria.getConsumedTokenOutputMin(), criteria.getConsumedTokenOutputMax());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("packagingPlan","id", criteria.getPackagingPlan()==null?null:criteria.getPackagingPlan().getId());
        addPredicateFk("packagingPlan","id", criteria.getPackagingPlans());
        addPredicateFk("packagingPlan","code", criteria.getPackagingPlan()==null?null:criteria.getPackagingPlan().getCode());
    }

    public InscriptionCollaboratorSpecification(InscriptionCollaboratorCriteria criteria) {
        super(criteria);
    }

    public InscriptionCollaboratorSpecification(InscriptionCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
