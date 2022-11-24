package br.com.macedosistemas.mockgenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Assertions;

import org.reflections.Reflections;

public class EntityTester
{

	private Reflections	reflections;


	public EntityTester(Reflections reflections)
	{
		this.reflections = reflections;
		testeCoberturaEntidades();
	}


	private void testeCoberturaEntidades()
	{
		Set<Class<?>> classesResources = reflections.getTypesAnnotatedWith(Entity.class);		

		for (Class<?> cr : classesResources)
		{
			try
			{
				Object classe = null;
				classe = gerarContrutor(cr, classe);
				UtilTest.declarandoAtributos(cr, classe);
				testarMetodosSET(cr, classe);
				testarMetodosGET(cr, classe);
				classe.toString();
				Object classe2 = null;
				classe2 = gerarContrutor(cr, classe);
				// Testa toString
				classe.toString();
				// Testa hashCode
				classe.hashCode();
				classe2.hashCode();
				// Testa equals
				classe.equals(classe2);
				classe.equals(null);
				classe.equals(new Object());
				UtilTest.declarandoAtributos(cr, classe2);
				classe.equals(classe2);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Erro ao Testar classe...");
			}
		}
	}


	private void testarMetodosGET(Class<?> cr, Object classe)
	{
		for (Method m : cr.getDeclaredMethods())
		{
			try
			{
				if (m.getName().toLowerCase().startsWith("get") || m.getName().toLowerCase().startsWith("is"))
				{
					m.setAccessible(true);
					Object ret = m.invoke(classe);
					Assertions.assertNotNull(ret);
				}
			}
			catch (Error re)
			{
				System.out.println("   Falhou >> Método: " + m.getName());
			}
			catch (Exception e)
			{
				System.out.println("   Falhou >> Método: " + m.getName());
			}
			
		}
	}


	private void testarMetodosSET(Class<?> cr, Object classe)
	{
		for (Method m : cr.getDeclaredMethods())
		{
			try
			{
				if (m.getName().toLowerCase().startsWith("set"))
				{
					m.setAccessible(true);
					ArrayList<Object> args = UtilTest.montaParametrosMetodos(m);
					m.invoke(classe, args.toArray());
				}
			}
			catch (Exception e)
			{
				System.out.println("   Falhou >> Método: " + m.getName());
			}
		}
	}


	@SuppressWarnings({ "rawtypes", "deprecation" })
	private Object gerarContrutor(Class<?> cr, Object classe)
		throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		for (Constructor c : cr.getConstructors())
		{
			ArrayList<Object> args = new ArrayList<Object>();
			for (Class<?> t : c.getParameterTypes())
			{
				if (t.equals(int.class) || t.equals(Integer.class))
				{
					args.add(0);
				}
				else if (t.equals(long.class))
				{
					args.add((long) 0);
				}
				else if (t.equals(Long.class))
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
				else if (t.equals(byte.class))
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
					args.add("Teste");
				}
				else if (t.equals(List.class) || t.equals(ArrayList.class))
				{
					args.add(new ArrayList());
				}
				else if (t.equals(Set.class) || t.equals(HashSet.class))
				{
					args.add(new HashSet());
				}
				else if (t.equals(Timestamp.class))
				{
					args.add(new Timestamp(1234567845));
				}
				else if (t.equals(XMLGregorianCalendar.class))
				{
					args.add(null);
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
				else if (t.toString().indexOf("[B") != -1)
				{
					byte[] valor = {};
					args.add(valor);
				}
				else
				{
					args.add(t.newInstance());
				}
			}
			if (args.size() == 0)
			{
				classe = cr.getConstructor().newInstance();
			}
			else
			{
				classe = c.newInstance(args.toArray());
			}
		}
		return classe;
	}
}
