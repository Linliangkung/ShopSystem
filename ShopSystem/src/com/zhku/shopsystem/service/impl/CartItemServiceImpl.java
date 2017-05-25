package com.zhku.shopsystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhku.shopsystem.dao.CartItemDao;
import com.zhku.shopsystem.dao.ProductDao;
import com.zhku.shopsystem.domain.CartItem;
import com.zhku.shopsystem.domain.Product;
import com.zhku.shopsystem.exception.MessageException;
import com.zhku.shopsystem.service.CartItemService;
import com.zhku.shopsystem.utils.Cart;

public class CartItemServiceImpl implements CartItemService {
	private CartItemDao cartItemDao;
	private ProductDao productDao;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MessageException.class)
	public void addCartItem(CartItem cartItem) throws MessageException {
		// 1.根据商品id ,获取商品
		Product product = productDao.getByIdInShareMode(cartItem.getProduct().getPid());
		// 2.判断当前商品的库存是否不足
		if (product.getPnum() <= 0) {
			throw new MessageException("库存不足");
		}
		//3.判断当前购物车项的商品的数量是否超过库存
		if(cartItem.getQuantity()>product.getPnum()){
			throw new MessageException("数量超出库存");
		}
		cartItem.setProduct(product);
		// 4.判断当前购物车项是否存在
		DetachedCriteria criteria = DetachedCriteria.forClass(CartItem.class);
		criteria.add(Restrictions.eq("user.uid", cartItem.getUser().getUid()))
				.add(Restrictions.eq("product.pid", cartItem.getProduct().getPid()));
		CartItem queryCartItem = cartItemDao.getByDetachedCriteria(criteria);
		if (queryCartItem != null) {
			// 说明购物车项已经存在
			Integer lastQuantity = queryCartItem.getQuantity();
			Integer nowQuantity = lastQuantity + cartItem.getQuantity();
			if (nowQuantity > product.getPnum()) {
				throw new MessageException("数量超出库存");
			}
			queryCartItem.setQuantity(nowQuantity);
			cartItem = queryCartItem;
		}
		cartItemDao.saveOrUpdate(cartItem);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public List<CartItem> getCartItemsByUid(Integer uid) {

		return cartItemDao.getCartItemsByUid(uid);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MessageException.class)
	public void increseByCiid(Integer ciid) throws MessageException {
		// 1.获取购物车项
		CartItem cartItem = cartItemDao.getById(ciid);
		// 2.判断当前购物车项商品的数量是否等于商品的库存
		if (cartItem.getQuantity()>=cartItem.getProduct().getPnum()) {
			// 说明当前购物车项商品的数量已经达到最大值，再加一就会超出商品的库存
			throw new MessageException("数量超出库存");
		}
		cartItem.setQuantity(cartItem.getQuantity() + 1);
		cartItemDao.update(cartItem);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void decreaseByCiid(Integer ciid) {
		// 1.获取购物车项
		CartItem cartItem = cartItemDao.getById(ciid);
		// 2.判断当前购物车项商品的数量是否等于1
		if(cartItem.getQuantity()>1){
			cartItem.setQuantity(cartItem.getQuantity() - 1);
			cartItemDao.update(cartItem);
		}
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(CartItem cartItem) {
		cartItemDao.delete(cartItem);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteCartItemsByUid(Integer uid) {
		cartItemDao.deleteCartItemsByUid(uid);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public Cart getCartBeanByUid(Integer uid) {
		//1.调用cartItemDao根据用户的id查询出所有购物车项
		List<CartItem> cartItems=cartItemDao.getCartItemsByUid(uid);
		//3.封装购物车对象
		Map<Integer,List<CartItem>> cart=new HashMap<Integer,List<CartItem>>();
		for(CartItem cartItem:cartItems){
			Integer sid=cartItem.getProduct().getSeller().getSid();
			//先判断购物车中是否已经存在对应商家的商品
			if(cart.containsKey(sid)){
				//如果存在
				List<CartItem> cartItemList = cart.get(sid);
				if(cartItemList!=null){
					cartItemList.add(cartItem);
				}
			}else{
				//如果不存在
				List<CartItem> cartItemList=new ArrayList<CartItem>();
				cartItemList.add(cartItem);
				cart.put(sid, cartItemList);
			}
		}
		Double totalMoney=0d;
		for(CartItem cartItem:cartItems){
			Double money=cartItem.getProduct().getShop_price()*cartItem.getQuantity();
			totalMoney=totalMoney+money;
		}
		Cart cartBean=new Cart();
		cartBean.setCart(cart);
		cartBean.setTotalMoney(totalMoney);
		return cartBean;
	}
	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	
}
