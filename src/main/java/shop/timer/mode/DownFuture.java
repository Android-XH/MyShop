package shop.timer.mode;

import com.taobao.api.response.TbkDgMaterialOptionalResponse;

import java.util.List;
import java.util.concurrent.Future;

public class DownFuture{
    private boolean isSave;
    private Future<List<TbkDgMaterialOptionalResponse.MapData>> future;

    public DownFuture(Future<List<TbkDgMaterialOptionalResponse.MapData>> future) {
        this.future = future;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public Future<List<TbkDgMaterialOptionalResponse.MapData>> getFuture() {
        return future;
    }

    public void setFuture(Future<List<TbkDgMaterialOptionalResponse.MapData>> future) {
        this.future = future;
    }
}