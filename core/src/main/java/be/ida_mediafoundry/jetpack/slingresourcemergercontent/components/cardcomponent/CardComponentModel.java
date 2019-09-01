package be.ida_mediafoundry.jetpack.slingresourcemergercontent.components.cardcomponent;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardComponentModel {

    @ValueMapValue(name = "title")
    private String title;

    @ValueMapValue(name = "description")
    private String description;

    @ResourcePath(name = "link")
    private Resource linkedResource;

    @PostConstruct
    private void init(){
        if(linkedResource!=null){
            LinkedResourceModel linkedResourceModel = linkedResource.adaptTo(LinkedResourceModel.class);
            if(linkedResourceModel != null){
                if(StringUtils.isEmpty(title)){
                    title = linkedResourceModel.getTitle();
                }
                if(StringUtils.isEmpty(description)){
                    description = linkedResourceModel.getDescription();
                }
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
