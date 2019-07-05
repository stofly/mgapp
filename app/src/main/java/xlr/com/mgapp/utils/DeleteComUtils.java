package xlr.com.mgapp.utils;

import org.apache.commons.lang.xwork.StringUtils;

import java.util.*;

/**
 * @author 青铜骑士
 * @ClassName: DeleteComUtils
 * @ProjectName mgapp
 * @Description: TODO
 * @date 2019/6/921:30
 */
public class DeleteComUtils {
    public static boolean equals(List<String> lis1 , List<String> lis2) {
        Collections.sort(lis1);
        System.out.println(lis1);
        Collections.sort(lis2);
        System.out.println(lis2);
        return lis1.equals(lis2);
    }
}
