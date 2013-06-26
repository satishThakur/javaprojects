package com.satish.usertype.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;



public class MonitoryAmountUserType implements UserType {
	private int[] m_sqlTypes = new int[]{org.hibernate.type.StandardBasicTypes.BIG_DECIMAL.sqlType(),
			org.hibernate.type.StandardBasicTypes.STRING.sqlType()};
	@Override
	public int[] sqlTypes() {
		return m_sqlTypes;
	}

	@Override
	public Class<?> returnedClass() {
		return MonitoryAmount.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if(x == null && y == null)
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
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		BigDecimal bd = rs.getBigDecimal(names[0]);
		String currencyCode = rs.getString(names[1]);
		if(rs.wasNull())
			return null;
		MonitoryAmount amount = new MonitoryAmount(bd, currencyCode);
		return amount;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, m_sqlTypes[0]);
			st.setNull(index+1, m_sqlTypes[1]);
		}
		MonitoryAmount amount = (MonitoryAmount)value;
		
		
		st.setBigDecimal(index, amount.getAmount());
		st.setString(index+1, amount.getCurrency().getCurrencyCode());

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
	public Serializable disassemble(Object value) throws HibernateException {
		
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		
		return original;
	}

}
