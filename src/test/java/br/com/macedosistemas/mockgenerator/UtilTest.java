package br.com.macedosistemas.mockgenerator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.mockito.Mockito;

public class UtilTest {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<Object> montaParametrosMetodos(Method m)
		throws InstantiationException, IllegalAccessException, NoSuchFieldException
	{
		ArrayList<Object> args = new ArrayList<Object>();
		for (Class<?> t : m.getParameterTypes())
		{
			if (t.equals(int.class) || t.equals(Integer.class))
			{
				args.add(0);
			}
			else if (t.equals(long.class) || t.equals(Long.class))
			{
				args.add((long) 0);
			}
			else if (t.equals(short.class) || t.equals(Short.class))
			{
				args.add((short) 0);
			}
			else if (t.equals(BigDecimal.class))
			{
				args.add(new BigDecimal(0));
			}
			else if (t.equals(double.class) || t.equals(Double.class))
			{
				args.add(0.0);
			}
			else if (t.equals(byte.class) || t.equals(Byte.class))
			{
				args.add((byte) 0);
			}
			else if (t.equals(boolean.class) || t.equals(Boolean.class))
			{
				args.add(true);
			}
			else if (t.equals(char.class) || t.equals(Character.class))
			{
				args.add('0');
			}
			else if (t.equals(String.class))
			{
				args.add("N");
			}
			else if (t.equals(List.class) || t.equals(ArrayList.class))
			{
				args.add(new ArrayList());
			}
			else if (t.equals(Set.class) || t.equals(HashSet.class))
			{
				args.add(new HashSet());
			}
			else if (t.equals(Date.class))
			{
				args.add(UtilTest.formatDate("2020-01-01"));
			}
			else if (t.equals(Time.class))
			{
				args.add(UtilTest.formatTime("10:00"));
			}
			else if (t.equals(LocalDate.class))
			{
				args.add(LocalDate.now());
			}
			else if (t.equals(LocalTime.class))
			{
				args.add(LocalTime.now());
			}
			else if (t.equals(LocalDateTime.class))
			{
				args.add(LocalDateTime.now());
			}
			else if (t.equals(Timestamp.class))
			{
				args.add(new Timestamp(1234567845));
			}
			else if (t.equals(XMLGregorianCalendar.class))
			{
				args.add(null);
			}
			else if (t.toString().indexOf("[B") != -1)
			{
				byte[] valor = {};
				args.add(valor);
			}
			else
			{
				@SuppressWarnings("deprecation")
				Object obj = t.newInstance();
				inicializarAtributosClasse(obj);
				args.add(obj);
			}
		}
		return args;
	}


	@SuppressWarnings({ "rawtypes" })
	public static void declarandoAtributos(Class<?> cr, Object classe)
		throws Exception
	{
		for (Field fcr : cr.getDeclaredFields())
		{
			if (!java.lang.reflect.Modifier.isStatic(fcr.getModifiers()))
			{
				Field field = cr.getDeclaredField(fcr.getName());
				field.setAccessible(true);
				if (fcr.getType().equals(int.class) || fcr.getType().equals(Integer.class))
				{
					field.set(classe, 0);
				}
				else if (fcr.getType().equals(long.class) || fcr.getType().equals(Long.class))
				{
					field.set(classe, (long) 0);
				}
				else if (fcr.getType().equals(short.class) || fcr.getType().equals(Short.class))
				{
					field.set(classe, (short) 0);
				}
				else if (fcr.getType().equals(BigDecimal.class))
				{
					field.set(classe, new BigDecimal(0));
				}
				else if (fcr.getType().equals(double.class) || fcr.getType().equals(Double.class))
				{
					field.set(classe, 0.0);
				}
				else if (fcr.getType().equals(byte.class) || fcr.getType().equals(Byte.class))
				{
					field.set(classe, (byte) 0);
				}
				else if (fcr.getType().equals(boolean.class) || fcr.getType().equals(Boolean.class))
				{
					field.set(classe, true);
				}
				else if (fcr.getType().equals(char.class) || fcr.getType().equals(Character.class))
				{
					field.set(classe, '0');
				}
				else if (fcr.getType().equals(Date.class))
				{
					field.set(classe, UtilTest.formatDate("2020-01-01"));
				}
				else if (fcr.getType().equals(Time.class))
				{
					field.set(classe, UtilTest.formatTime("10:00"));
				}
				else if (fcr.getType().equals(LocalDate.class))
				{
					field.set(classe, LocalDate.now());
				}
				else if (fcr.getType().equals(LocalTime.class))
				{
					field.set(classe, LocalTime.now());
				}
				else if (fcr.getType().equals(LocalDateTime.class))
				{
					field.set(classe, LocalDateTime.now());
				}
				else if (fcr.getType().equals(String.class))
				{
					field.set(classe, "N");
				}
				else if (fcr.getType().equals(List.class))
				{
					field.set(classe, new ArrayList());
				}
				else if (fcr.getType().equals(Set.class))
				{
					field.set(classe, new HashSet());
				}
				else if (fcr.getType().toString().indexOf("[B") != -1)
				{
					byte[] valor = {};
					field.set(classe, valor);
				}
				else if (fcr.getType().equals(Timestamp.class))
				{
					field.set(classe, new Timestamp(1234567845));
				}
				else if (fcr.getType().equals(XMLGregorianCalendar.class))
				{
					field.set(classe, null);
				}
				else if (fcr.getType().toString().toLowerCase().indexOf("enum") != -1)
				{
					//field.set(classe, valor);
				}				
				else
				{
					field.set(classe, Mockito.mock(fcr.getType()));
				}
			}
		}
	}


	@SuppressWarnings("rawtypes")
	public static void inicializarAtributosClasse(Object classeMocar)
		throws NoSuchFieldException, IllegalAccessException, InstantiationException
	{
		if (classeMocar.getClass().getDeclaredFields().length > 0)
		{
			for (Field fc : classeMocar.getClass().getDeclaredFields())
			{
				if (!java.lang.reflect.Modifier.isStatic(fc.getModifiers()))
				{
					Field campo = classeMocar.getClass().getDeclaredField(fc.getName());
					campo.setAccessible(true);
					if (fc.getType().equals(int.class) || fc.getType().equals(Integer.class))
					{
						campo.set(classeMocar, 0);
					}
					else if (fc.getType().equals(short.class) || fc.getType().equals(Short.class))
					{
						campo.set(classeMocar, (short) 0);
					}
					else if (fc.getType().equals(long.class) || fc.getType().equals(Long.class))
					{
						campo.set(classeMocar, (long) 0);
					}
					else if (fc.getType().equals(BigDecimal.class))
					{
						campo.set(classeMocar, new BigDecimal(0));
					}
					else if (fc.getType().equals(double.class) || fc.getType().equals(Double.class))
					{
						campo.set(classeMocar, 0.0);
					}
					else if (fc.getType().equals(byte.class) || fc.getType().equals(Byte.class))
					{
						campo.set(classeMocar, (byte) 0);
					}
					else if (fc.getType().equals(boolean.class) || fc.getType().equals(Boolean.class))
					{
						campo.set(classeMocar, true);
					}
					else if (fc.getType().equals(char.class) || fc.getType().equals(Character.class))
					{
						campo.set(classeMocar, '0');
					}
					else if (fc.getType().equals(Date.class))
					{
						campo.set(classeMocar, UtilTest.formatDate("2020-01-01"));
					}
					else if (fc.getType().equals(Time.class))
					{
						campo.set(classeMocar, UtilTest.formatTime("10:00"));
					}
					else if (fc.getType().equals(LocalDate.class))
					{
						campo.set(classeMocar, LocalDate.now());
					}
					else if (fc.getType().equals(LocalTime.class))
					{
						campo.set(classeMocar, LocalTime.now());
					}
					else if (fc.getType().equals(LocalDateTime.class))
					{
						campo.set(classeMocar, LocalDateTime.now());
					}
					else if (fc.getType().equals(String.class))
					{
						campo.set(classeMocar, "N");
					}
					else if (fc.getType().equals(List.class) || fc.getType().equals(ArrayList.class)
							|| fc.getType().equals(Collection.class))
					{
						campo.set(classeMocar, new ArrayList());
					}
					else if (fc.getType().equals(Set.class) || fc.getType().equals(HashSet.class))
					{
						campo.set(classeMocar, new HashSet());
					}
					else if (fc.getType().equals(Timestamp.class))
					{
						campo.set(classeMocar, new Timestamp(1234567845));
					}
					else if (fc.getType().toString().indexOf("[B") != -1)
					{
						byte[] valor = {};
						campo.set(classeMocar, valor);
					}
					else
					{
						try
						{
							@SuppressWarnings("deprecation")
							Object obj = fc.getType().newInstance();
							//inicializarAtributosClasse(obj);
							campo.set(classeMocar, obj);
						}
						catch (Exception e)
						{
							Object obj = Mockito.mock(fc.getType());
							campo.set(classeMocar, obj);
						}
					}
				}
			}
		}
	}

	public static Date formatDate(String dt)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date data = null;
		try
		{
			data = new Date(sdf.parse(dt).getTime());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return data;
	}

	public static Time formatTime(String str)
	{
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

		Time time = null;
		try
		{
			Date data = new Date(formatador.parse(str).getTime());
			time = new Time(data.getTime());	
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return time;
	}	
}
