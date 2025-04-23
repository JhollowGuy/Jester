package jester;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {
    private List<GameObject> objects = new ArrayList<>();

    public void add(GameObject obj) {
        objects.add(obj);
    }

    public void update(float dt) {
        for (GameObject obj : objects) {
            obj.update(dt);
        }
    }

    public void render() {
        for (GameObject obj : objects) {
            obj.render(Jester.graphics);
        }
    }

    public void clear() {
        objects.clear();
    }
}
