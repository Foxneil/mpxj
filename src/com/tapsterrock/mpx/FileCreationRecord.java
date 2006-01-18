/*
 * file:       FileCreationRecord.java
 * author:     Scott Melville
 *             Jon Iles
 * copyright:  (c) Tapster Rock Limited 2002-2003
 * date:       15/08/2002
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package com.tapsterrock.mpx;

import java.util.Locale;


/**
 * This class represents the first record to appear in an MPX file. This record
 * identifies the file type, version number, originating software and the
 * separator to be used in the remainder of the file.
 */
public final class FileCreationRecord extends MPXRecord
{
   /**
    * Default constructor.
    *
    * @param file the parent file to which this record belongs.
    */
   FileCreationRecord (ProjectFile file)
   {
      super(file);

      setLocale(file.getLocale());
      setFileVersion(FileVersion.VERSION_4_0);
   }

   /**
    * This method is called when the locale of the parent file is updated.
    * It resets the locale specific currency attributes to the default values
    * for the new locale.
    *
    * @param locale new locale
    */
   void setLocale (Locale locale)
   {
      setDelimiter(LocaleData.getChar(locale, LocaleData.FILE_DELIMITER));
      setProgramName(LocaleData.getString(locale, LocaleData.PROGRAM_NAME));
      setCodePage((CodePage)LocaleData.getObject(locale, LocaleData.CODE_PAGE));
   }

   /**
    * This method allows the default file creation record to be updated
    * with values read from an MPX file.
    *
    * @param record record containing the data for this object.
    */
   void setValues (Record record)
   {
      setProgramName(record.getString(0));
      setFileVersion(FileVersion.getInstance(record.getString(1)));
      setCodePage(record.getCodePage(2));
   }

   /**
    * Sets the delimiter character, "," by default.
    *
    * @param delimiter delimiter character
    */
   public void setDelimiter (char delimiter)
   {
      m_delimiter = delimiter;
      getParentFile().setDelimiter(m_delimiter);
   }

   /**
    * Retrieves the delimiter character, "," by default.
    *
    * @return delimiter character
    */
   public char getDelimiter ()
   {
      return (m_delimiter);
   }

   /**
    * Program name file created by.
    *
    * @param programName system name
    */
   public void setProgramName (String programName)
   {
      m_programName = programName;
   }

   /**
    * Program name file created by.
    *
    * @return program name
    */
   public String getProgramName ()
   {
      return (m_programName);
   }

   /**
    * Version of the MPX file.
    *
    * @param version MPX file version
    */
   public void setFileVersion (FileVersion version)
   {
      m_fileVersion = version;
   }

   /**
    * Version of the MPX file.
    *
    * @return MPX file version
    */
   public FileVersion getFileVersion ()
   {
      return (m_fileVersion);
   }

   /**
    * Sets the codepage.
    *
    * @param codePage code page type
    */
   public void setCodePage (CodePage codePage)
   {
      m_codePage = codePage;
   }

   /**
    * Retrieves the codepage.
    *
    * @return code page type
    */
   public CodePage getCodePage ()
   {
      return (m_codePage);
   }

   /**
    * The character to be used throughout as a delimiter for MPX files.
    */
   private char m_delimiter;

   /**
    * The program and version number used to create the file.
    */
   private String m_programName;

   /**
    * The version number of the MPX file format used in the file.
    */
   private FileVersion m_fileVersion;

   /**
    * The code page used to create the file.
    */
   private CodePage m_codePage;
}
