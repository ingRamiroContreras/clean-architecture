package banking.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class PaginationHelper {

    public Map<String, Object> getPagination(Criteria criteria, String id, java.util.Optional<String> pag, java.util.Optional<String> pagLength) {
        Map<String, Object> result = new HashMap<String, Object>();
        List lista = criteria.list();

        Integer totalResultCount = lista.size();

        Integer total = 0;

        if (lista.size() > 0) {
            total = 1;
            if (pag.isPresent() && !pag.get().isEmpty() && pagLength.isPresent() && !pagLength.get().isEmpty()) {
                criteria.setProjection(Projections.distinct(Projections.property("id")));
                criteria.addOrder(Order.desc("id"));
                criteria.setFirstResult(Integer.parseInt(pagLength.get()) * (Integer.parseInt(pag.get()) - 1));
                criteria.setMaxResults(Integer.parseInt(pagLength.get()));
                List uniqueSubList = criteria.list();

                criteria.setProjection(null);
                criteria.setFirstResult(0);
                criteria.setMaxResults(Integer.MAX_VALUE);
                criteria.add(Restrictions.in("id", uniqueSubList));
                criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

                lista = criteria.list();

                total = totalResultCount / Integer.parseInt(pagLength.get());
                if ((totalResultCount % Integer.parseInt(pagLength.get())) > 0) {
                    total = total + 1;
                }
            }
        }

        result.put("lista", lista);
        result.put("totalPage", total);
        result.put("totalResultCount", totalResultCount);

        return result;
    }

    
}
