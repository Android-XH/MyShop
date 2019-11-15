package shop.mode.extension;

import shop.annotation.ORMAnnotation.JoinColumn;
import shop.annotation.ORMAnnotation.ManyToOne;
import shop.mode.Material;
import shop.mode.base.BasePOJO;

public class MaterialItemExtension extends BasePOJO {
    @ManyToOne
    @JoinColumn(pName = "pid",name = "pid")
    private Material Material;

    public shop.mode.Material getMaterial() {
        return Material;
    }

    public void setMaterial(shop.mode.Material material) {
        Material = material;
    }
}
