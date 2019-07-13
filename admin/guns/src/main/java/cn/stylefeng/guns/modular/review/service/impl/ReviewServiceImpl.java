package cn.stylefeng.guns.modular.review.service.impl;

import cn.stylefeng.guns.modular.system.model.Review;
import cn.stylefeng.guns.modular.system.dao.ReviewMapper;
import cn.stylefeng.guns.modular.review.service.IReviewService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 时评表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-24
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements IReviewService {

}
