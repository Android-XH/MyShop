package shop.mode.extension;

import shop.annotation.ORMAnnotation.JoinColumn;
import shop.annotation.ORMAnnotation.OneToMany;
import shop.mode.MaterialItem;
import shop.mode.base.BasePOJO;

import java.util.List;

public class MaterialExtension extends BasePOJO {
    @OneToMany
    @JoinColumn(pName = "pid",name = "pid")
    private List<MaterialItem> materialItems;

    public List<MaterialItem> getMaterialItems() {
        return materialItems;
    }

    public void setMaterialItems(List<MaterialItem> materialItems) {
        this.materialItems = materialItems;
    }
}