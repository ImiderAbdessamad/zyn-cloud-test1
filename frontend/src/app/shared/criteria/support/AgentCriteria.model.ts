
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class AgentCriteria extends BaseCriteria {

    public description: string;
    public descriptionLike: string;
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

}
