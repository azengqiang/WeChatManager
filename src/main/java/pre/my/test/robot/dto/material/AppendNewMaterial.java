package pre.my.test.robot.dto.material;

import pre.my.test.robot.dto.material.detail.BaseNews;

/**
 * Author:qiang.zeng on 2017/3/31.
 */
public class AppendNewMaterial {
    private BaseNews[] articles;

    public BaseNews[] getArticles() {
        return articles;
    }

    public void setArticles(BaseNews[] articles) {
        this.articles = articles;
    }
}
