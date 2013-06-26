package com.satish.usertype.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;



public class MonitoryAmountUserType implements CompositeUserType {
	private Type[] m_types = new Type[]{org.hibernate.type.StandardBasicTypes.BIG_DECIMAL,
			org.hibernate.type.StandardBasicTypes.STRING};


	@Override
	public Class<?> returnedClass() {
		return MonitoryAmount.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if(x == y)
			return true;
		if(x == null )
			return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {

		return x.hashCode();
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {

		return false;
	}

	@Override
	public String[] getPropertyNames() {
		return new String[]{"amount", "currencyCode"};
	}

	@Override
	public Type[] getPropertyTypes() {
		return m_types;
	}

	@Override
	public Object getPropertyValue(Object component, int property)
	throws HibernateException {
		MonitoryAmount amount = (MonitoryAmount)component;

		if(property == 0)
			return amount.getAmount();
		return amount.getCurrency();
	}

	@Override
	public void setPropertyValue(Object component, int property, Object value)
	throws HibernateException {


	}	

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
	throws HibernateException, SQLException {
		BigDecimal bd = rs.getBigDecimal(names[0]);
		String currencyCode = rs.getString(names[1]);
		if(rs.wasNull())
			return null;
		MonitoryAmount amount = new MonitoryAmount(bd, currencyCode);
		return amount;
	}
	
	

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, org.hibernate.type.StandardBasicTypes.BIG_DECIMAL.sqlType());
			st.setNull(index+1,org.hibernate.type.StandardBasicTypes.STRING.sqlType());
		}
		MonitoryAmount amount = (MonitoryAmount)value;


		st.setBigDecimal(index, amount.getAmount());
		st.setString(index+1, amount.getCurrency().getCurrencyCode());

	}

	@Override
	public Serializable disassemble(Object value, SessionImplementor session)
	throws HibernateException {
		
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, SessionImplementor session,
			Object owner) throws HibernateException {
		
		return cached;
	}

	@Override
	public Object replace(Object original, Object target,
			SessionImplementor session, Object owner) throws HibernateException {
		
		return original;
	}

}
