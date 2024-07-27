import {CountryCriteria} from './CountryCriteria.model';
import {CityCriteria} from './CityCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CollaboratorCriteria extends BaseCriteria {

    public description: string;
    public descriptionLike: string;
    public postal: string;
    public postalLike: string;
    public credentialsNonExpired: null | boolean;
    public enabled: null | boolean;
    public accountNonExpired: null | boolean;
    public accountNonLocked: null | boolean;
    public passwordChanged: null | boolean;
    public username: string;
    public usernameLike: string;
    public password: string;
    public passwordLike: string;
    public avatar: string;
    public avatarLike: string;
    public fullName: string;
    public fullNameLike: string;
    public about: string;
    public aboutLike: string;
  public city: CityCriteria ;
  public citys: Array<CityCriteria> ;

}
